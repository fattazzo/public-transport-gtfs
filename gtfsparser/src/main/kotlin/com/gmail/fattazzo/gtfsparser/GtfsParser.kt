package com.gmail.fattazzo.gtfsparser

import com.gmail.fattazzo.gtfsparser.exceptions.FileDownloadException
import com.gmail.fattazzo.gtfsparser.files.processor.FileProcessorFactory
import com.gmail.fattazzo.gtfsparser.listener.GtfsParseEvent
import com.gmail.fattazzo.gtfsparser.listener.ParseAction
import com.gmail.fattazzo.gtfsparser.listener.ParseListener
import com.gmail.fattazzo.gtfsparser.reader.CsvReader
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.ZipInputStream


/**
 * @author fattazzo
 *         <p/>
 *         date: 19/01/18
 */
class GtfsParser {

    private var parseListener: ParseListener? = null

    private var stopProcess = false

    private var isRunning = false

    /**
     * Download and parse GTFS files
     *
     * @param feedId feed id
     * @param url of zip file
     * @param sqlBlockSize entries parse block size
     */
    fun parse(feedId: String, url: URL, sqlBlockSize: Int = 1000) {
        isRunning = true

        fireEvent(ParseAction.PARSE_STARTED)

        var inputStream: InputStream? = null
        var zipInputStream: ZipInputStream? = null
        try {
            fireEvent(ParseAction.DOWNLOAD_STARTED)
            inputStream = download(url)
            fireEvent(ParseAction.DOWNLOAD_FINISHED)

            zipInputStream = ZipInputStream(inputStream)

            var entry = zipInputStream.nextEntry
            while (entry != null) {
                val reader = CsvReader(InputStreamReader(zipInputStream, "UTF-8"))
                reader.readHeaders()

                val fileProcessor = FileProcessorFactory.getProcessor(entry.name)
                if (fileProcessor == null) {
                    entry = zipInputStream.nextEntry
                    continue
                }

                val inserts = mutableListOf<String>()
                var idx = 0
                var count = 0
                while (reader.readRecord()) {
                    if (stopProcess) {
                        fireEvent(ParseAction.PARSE_ABORTED)
                        return
                    }

                    val columnValuesMap = reader.headers.map {
                        it.replace("\uFEFF", "") to
                                reader.get(it)
                                        .replace("\uFEFF", "")
                                        .replace("\'", "\'\'")
                    }.toMap()
                    val sqlInsert = fileProcessor.createSQLInsert(feedId, columnValuesMap)
                            ?: continue

                    inserts.add(sqlInsert)
                    idx++
                    if (idx == sqlBlockSize) {
                        count += inserts.size
                        fireEvent(ParseAction.ENTRY_IMPORT, fileProcessor.fileSpec.tableName(), count, inserts)
                        idx = 0
                        inserts.clear()
                    }
                }

                if (inserts.isNotEmpty()) {
                    count += inserts.size
                    fireEvent(ParseAction.ENTRY_IMPORT, fileProcessor.fileSpec.tableName(), count, inserts)
                }

                zipInputStream.closeEntry()
                entry = zipInputStream.nextEntry
            }
        } catch (e: Exception) {
            fireEvent(ParseAction.PARSE_ABORTED)
        } finally {
            if (!stopProcess) {
                fireEvent(ParseAction.PARSE_FINISHED)
            }

            isRunning = false
            stopProcess = false

            if (zipInputStream != null)
                zipInputStream.close()
            if (inputStream != null)
                inputStream.close()
        }
    }


    @Throws(FileDownloadException::class)
    private fun download(url: URL): InputStream {

        val connection = url.openConnection() as HttpURLConnection
        connection.instanceFollowRedirects = true
        connection.connect()

        if (connection.responseCode != HttpURLConnection.HTTP_OK) {
            val errorMsg = "Server returned HTTP " + connection.responseCode + " " + connection.responseMessage
            println(errorMsg)
            throw FileDownloadException(errorMsg)
        }

        return BufferedInputStream(connection.inputStream)
    }

    fun setParseListener(parseListener: ParseListener) {
        this.parseListener = parseListener
    }


    private fun fireEvent(parseAction: ParseAction, clazz: String?, count: Int?, inserts: MutableList<String>) {
        parseListener?.onEvent(GtfsParseEvent(parseAction, clazz, count), inserts)
    }

    private fun fireEvent(parseAction: ParseAction) {
        fireEvent(parseAction, null, null, mutableListOf())
    }

    fun stop() {
        if (isRunning)
            stopProcess = true
    }
}