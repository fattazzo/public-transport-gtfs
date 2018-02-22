package com.gmail.fattazzo.publictransportgtfs.gtfs.importer

import android.app.IntentService
import android.app.NotificationManager
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.activeandroid.ActiveAndroid
import com.gmail.fattazzo.gtfsdb.manager.DBManager
import com.gmail.fattazzo.gtfsparser.GtfsParser
import com.gmail.fattazzo.gtfsparser.listener.GtfsParseEvent
import com.gmail.fattazzo.gtfsparser.listener.ParseAction
import com.gmail.fattazzo.gtfsparser.listener.ParseListener
import com.gmail.fattazzo.publictransportgtfs.R
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Feed
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Operator
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.rest.ApiRestCLient
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EIntentService
import org.androidannotations.annotations.ServiceAction
import org.androidannotations.annotations.SystemService
import java.net.URL
import java.util.*


/**
 * @author fattazzo
 *         <p/>
 *         date: 20/02/18
 */
@EIntentService
open class GtfsImporterService : IntentService("GtfsImporterService") {

    @SystemService
    lateinit var notificationManager: NotificationManager

    @Bean
    lateinit var dbManager: DBManager

    private var operator: Operator? = null

    private var feeds: MutableList<Feed> = arrayListOf()

    private var builder: NotificationCompat.Builder? = null

    private val gtfsParser: GtfsParser by lazy {
        val parser = GtfsParser()

        parser.setParseListener(object : ParseListener {
            override fun onEvent(gtfsParseEvent: GtfsParseEvent, inserts: MutableList<String>) {
                updateNotification(gtfsParseEvent)

                try {
                    when (gtfsParseEvent.parseAction) {
                        ParseAction.PARSE_STARTED -> println("Start: " + Calendar.getInstance().time)
                        ParseAction.ENTRY_IMPORT -> importToDB(inserts)
                        ParseAction.PARSE_FINISHED -> {
                            println("Finish: " + Calendar.getInstance().time)
                            finishImport()
                        }
                        ParseAction.PARSE_ABORTED -> {
                            dbManager.recreate()
                            running = false
                        }
                        else -> {
                        }
                    }
                } catch (e: Exception) {
                    println(e.message)
                }

            }
        })
        parser
    }

    @ServiceAction
    fun importa(operatorId: String) {
        try {
            showSyncNotification()
            running = true

            loadFeeds(operatorId)

            feeds = feeds.filter { it.activeFeedVersion.orEmpty().isNotEmpty() }.toMutableList()
            if (feeds.isNotEmpty()) {
                dbManager.recreate()
                downloadAndParse(feeds[0])
            }
        } finally {
            running = false
        }
    }

    private fun downloadAndParse(feed: Feed) {
        try {
            val zipUrl = feed.url
            println("Zip url: " + zipUrl)
            gtfsParser.parse(feed.onestopId, URL(zipUrl), sqlBlockSize = 10000)
        } catch (e: Exception) {
            println(e.message)
            running = false
        }
    }

    private fun loadOperator(operatorId: String) {
        try {
            operator = ApiRestCLient.operatorService.loadOpetaror(operatorId).execute().body()
        } catch (e: Exception) {
            operator = null
            println(e.message)
        }
    }

    private fun loadFeeds(operatorId: String) {
        loadOperator(operatorId)

        feeds = arrayListOf()
        try {
            operator?.representedInFeedOnestopIds.orEmpty()
                    .forEach { feedId -> feeds.add(ApiRestCLient.feedsService.getFeed(feedId).execute().body()) }
        } catch (e: Exception) {
            feeds = arrayListOf()
        }
    }

    private fun showSyncNotification() {
        builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Importazione feed")
                //.setTicker(context.getString(R.string.text))
                .setAutoCancel(false)
                .setOnlyAlertOnce(true)
        notificationManager.notify(NOTIFICATION_ID, builder!!.build())
    }

    private fun updateNotification(event: GtfsParseEvent) {
        if (builder != null) {
            builder!!.setContentTitle("Importazione ${operator!!.name}")
            when (event.parseAction) {
                ParseAction.PARSE_STARTED -> builder!!.setContentText("Start")
                ParseAction.DOWNLOAD_STARTED -> builder!!.setContentText("Download data")
                ParseAction.DOWNLOAD_FINISHED -> builder!!.setContentText("Download data")
                ParseAction.ENTRY_IMPORT -> {
                    builder!!.setContentText("${event.gtfsEntry!!} ... ${event.count!!}")
                }
                ParseAction.PARSE_FINISHED -> builder!!.setContentText("Finish")
                ParseAction.PARSE_ABORTED -> builder!!.setContentText("Error")
            }

            notificationManager.notify(NOTIFICATION_ID, builder!!.build())
        }
    }

    open fun finishImport() {
        feeds.removeAt(0)

        if (feeds.isNotEmpty()) {
            downloadAndParse(feeds[0])
        } else {
            val operatordb = com.gmail.fattazzo.gtfsdb.entities.Operator.build(operator!!.country, operator!!.metro, operator!!.name, operator!!.shortName, operator!!.onestopId, operator!!.state, operator!!.website)
            operatordb.save()
            running = false
        }
    }

    open fun importToDB(inserts: MutableList<String>) {
        val db = ActiveAndroid.getDatabase()
        try {
            db.beginTransaction()
            for (insert in inserts) {
                db.execSQL(insert)
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
            gtfsParser.stop()
        } finally {
            db.endTransaction()
        }
    }

    override fun onHandleIntent(p0: Intent?) {

    }

    companion object {

        const val CHANNEL_ID = "gtfs_channel_01"
        const val NOTIFICATION_ID = 1

        var running = false
    }
}