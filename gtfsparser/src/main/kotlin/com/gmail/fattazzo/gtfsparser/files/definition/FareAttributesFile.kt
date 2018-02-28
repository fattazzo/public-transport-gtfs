package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.NUMBER
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class FareAttributesFile(
        override val name: String = "fare_attributes.txt",
        override val columnsHeader: Array<String> = arrayOf("fare_id", "price", "currency_type", "payment_method", "transfers", "agency_id", "transfer_duration"),
        override val columnsType: Array<DataType> = arrayOf(STRING, STRING, STRING, NUMBER, STRING, STRING, STRING))
    : FileDefinition