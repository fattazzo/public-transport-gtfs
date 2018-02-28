package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
interface FileDefinition {

    val name: String

    val columnsHeader: Array<String>

    val columnsType: Array<DataType>

    fun tableName(): String {
        return name.substringBefore(".")
    }
}