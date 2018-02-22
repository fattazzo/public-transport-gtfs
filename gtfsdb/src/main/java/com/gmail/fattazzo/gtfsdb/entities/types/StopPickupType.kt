package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 */
enum class StopPickupType {

    /**
     * Regularly scheduled pickup.
     */
    REGULARLY,

    /**
     * No pickup available.
     */
    NOT_AVAILABLE,

    /**
     * Must phone agency to arrange pickup.
     */
    MUST_PHONE,

    /**
     * Must coordinate with driver to arrange pickup.
     */
    MUST_COORDINATE
}