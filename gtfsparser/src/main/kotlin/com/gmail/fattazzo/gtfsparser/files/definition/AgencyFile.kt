package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class AgencyFile(
        override val name: String = "agency.txt",
        override val columnsHeader: Array<String> = arrayOf("agency_id", "agency_name", "agency_url", "agency_timezone", "agency_lang", "agency_phone", "agency_fare_url", "agency_email"),
        override val columnsType: Array<DataType> = arrayOf(STRING, STRING, STRING, STRING, STRING, STRING, STRING, STRING))
    : FileDefinition