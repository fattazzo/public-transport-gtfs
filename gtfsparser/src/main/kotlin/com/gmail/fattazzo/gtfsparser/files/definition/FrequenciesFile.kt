package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class FrequenciesFile(
        override val name: String = "frequencies.txt",
        override val columnsHeader: Array<String> = arrayOf("trip_id", "start_time", "end_time", "headway_secs", "exact_times"),
        override val columnsType: Array<DataType> = arrayOf(STRING,STRING,STRING,STRING,STRING))
    : FileDefinition