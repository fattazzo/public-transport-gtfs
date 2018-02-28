package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.NUMBER
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class StopTimesFile(
        override val name: String = "stop_times.txt",
        override val columnsHeader: Array<String> = arrayOf("trip_id", "arrival_time", "departure_time", "stop_id", "stop_sequence", "stop_headsign", "pickup_type", "drop_off_type", "shape_dist_traveled", "timepoint"),
        override val columnsType: Array<DataType> = arrayOf(STRING, STRING, STRING, STRING, STRING, STRING, NUMBER, NUMBER, STRING, STRING))
    : FileDefinition