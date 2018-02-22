package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 24/01/18
 */
enum class TripBikesAllowedType {

    /**
     * Indicates that there is no bike information for the trip.
     */
    NO_INFORMATION,

    /**
     * Indicates that the vehicle being used on this particular trip can accommodate at least one bicycle.
     */
    ALLOWED,

    /**
     * Indicates that no bicycles are allowed on this trip.
     */
    NOT_ALLOWED
}