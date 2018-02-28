package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class FareRulesFile(
        override val name: String = "fare_rules.txt",
        override val columnsHeader: Array<String> = arrayOf("fare_id", "route_id", "origin_id", "destination_id", "contains_id"),
        override val columnsType: Array<DataType> = arrayOf(STRING, STRING, STRING, STRING, STRING))
    : FileDefinition