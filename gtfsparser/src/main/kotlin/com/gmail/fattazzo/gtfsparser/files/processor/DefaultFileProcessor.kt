package com.gmail.fattazzo.gtfsparser.files.processor

import com.gmail.fattazzo.gtfsparser.files.definition.FileDefinition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class DefaultFileProcessor(val fileSpec: FileDefinition) {

    private var validHeaders = listOf<String>()

    private var insertHeader: String? = null

    private fun createColumnsInsert(headers: Set<String>): String? {

        validHeaders = headers.filter { fileSpec.columnsHeader.contains(it.replace("\uFEFF", "")) }

        return "feedId," + validHeaders.joinToString(",").replace("\uFEFF", "")
    }

    private fun createValues(feedId: String, columnValuesMap: Map<String, String>): String {
        return "'$feedId'," + validHeaders.map { columnValuesMap[it] }.joinToString(",")
    }

    fun createSQLInsert(feedId: String, columnValuesMap: Map<String, String>): String? {
        if (!isValid(columnValuesMap)) {
            return null
        }

        if (insertHeader == null) {
            insertHeader = String.format("INSERT INTO %s (%s) VALUES ", fileSpec.tableName(), createColumnsInsert(columnValuesMap.keys))
        }

        return insertHeader + " (" + createValues(feedId, columnValuesMap) + ")"
    }

    private fun isValid(columnValuesMap: Map<String, String>): Boolean {
        return true
    }
}