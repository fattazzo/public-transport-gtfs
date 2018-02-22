package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 */
enum class DateExceptionType {

    /**
     * Non used
     */
    NOT_VALID,

    /**
     * Indicates that service has been added for the specified date.
     */
    ADDED,

    /**
     * Indicates that service has been removed for the specified date.
     */
    REMOVED
}