package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.DATE
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class FeedInfoFile(
        override val name: String = "feed_info.txt",
        override val columnsHeader: Array<String> = arrayOf("feed_publisher_name", "feed_publisher_url", "feed_lang", "feed_start_date", "feed_end_date", "feed_version"),
        override val columnsType: Array<DataType> = arrayOf(STRING, STRING, STRING, DATE, DATE, STRING))
    : FileDefinition