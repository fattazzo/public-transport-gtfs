package com.gmail.fattazzo.gtfsparser.listener

/**
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 */
interface ParseListener {

    fun onEvent(gtfsParseEvent: GtfsParseEvent, inserts: MutableList<String>)
}