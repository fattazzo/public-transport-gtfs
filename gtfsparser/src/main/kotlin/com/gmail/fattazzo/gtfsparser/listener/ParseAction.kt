package com.gmail.fattazzo.gtfsparser.listener

/**
 * Actions generated on gtfs parse.
 *
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 */
enum class ParseAction {

    /**
     * Start process
     */
    PARSE_STARTED,

    /**
     * Finish process
     */
    PARSE_FINISHED,

    /**
     * process terminated with errors
     */
    PARSE_ABORTED,

    /**
     * Start file download process
     */
    DOWNLOAD_STARTED,

    /**
     * Finish file download process
     */
    DOWNLOAD_FINISHED,

    /**
     * GtfsEntry import process
     */
    ENTRY_IMPORT
}