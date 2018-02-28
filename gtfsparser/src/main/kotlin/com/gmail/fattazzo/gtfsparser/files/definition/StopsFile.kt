package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.NUMBER
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
open class StopsFile(
        override val name: String = "stops.txt",
        override val columnsHeader: Array<String> = arrayOf("stop_id", "stop_code", "stop_name", "stop_desc", "stop_lat", "stop_lon", "zone_id", "stop_url", "location_type", "parent_station", "stop_timezone", "wheelchair_boarding"),
        override val columnsType: Array<DataType> = arrayOf(STRING, STRING, STRING, STRING, NUMBER, NUMBER, STRING, STRING, NUMBER, STRING, STRING, NUMBER))
    : FileDefinition