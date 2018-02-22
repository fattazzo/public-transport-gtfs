package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
interface FileDefinition {

    val name: String

    val columnsHeader: Array<String>

    fun tableName(): String {
        return name.substringBefore(".")
    }
}