package com.gmail.fattazzo.gtfsparser.files.processor

import com.gmail.fattazzo.gtfsparser.files.definition.FileDefinitionFactory

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
object FileProcessorFactory {

    fun getProcessor(fileName : String) : DefaultFileProcessor? {
        return DefaultFileProcessor(FileDefinitionFactory.get(fileName)!!)
    }
}