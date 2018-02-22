package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 24/01/18
 */
enum class TripWheelchairAccessibleType {

    /**
     * Indicates that there is no accessibility information for the trip.
     */
    NO_INFORMATION,

    /**
     * Indicates that the vehicle being used on this particular trip can accommodate at least one rider in a wheelchair.
     */
    ACCESSIBLE,

    /**
     * Indicates that no riders in wheelchairs can be accommodated on this trip.
     */
    NOT_ACCESSIBLE
}