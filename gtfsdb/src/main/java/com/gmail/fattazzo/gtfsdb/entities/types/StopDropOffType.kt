package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 */
enum class StopDropOffType {

    /**
     * Regularly scheduled drop off.
     */
    REGULARLY,

    /**
     * No drop off available.
     */
    NOT_AVAILABLE,

    /**
     * Must phone agency to arrange drop off.
     */
    MUST_PHONE,

    /**
     * Must coordinate with driver to arrange drop off.
     */
    MUST_COORDINATE
}