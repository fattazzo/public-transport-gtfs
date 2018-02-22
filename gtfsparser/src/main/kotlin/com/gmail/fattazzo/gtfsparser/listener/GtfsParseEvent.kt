package com.gmail.fattazzo.gtfsparser.listener

/**
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 *
 * @property parseAction Action of event
 * @property gtfsEntry Reference entity if available, <code>null</code> otherwise
 * @property count Entity count if available, <code>null</code> otherwise
 *
 * @constructor Default constructor
 * @param parseAction Action of event
 * @param gtfsEntry Reference entity if available, <code>null</code> otherwise
 * @param count Entity count if available, <code>null</code> otherwise
 */
class GtfsParseEvent(val parseAction: ParseAction, val gtfsEntry: String? = null, val count: Int? = null) {

    override fun toString(): String {
        return "GtfsParseEvent(parseAction=$parseAction, gtfsEntry=$gtfsEntry, count=$count)"
    }
}