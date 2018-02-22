package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 */
enum class StopLocationType {

    /**
     * A location where passengers board or disembark from a transit vehicle.
     */
    STOP,

    /**
     * A physical structure or area that contains one or more stop.
     */
    STATION,

    /**
     * A location where passengers can enter or exit a station from the street. The stop entry must also specify a parent_station value referencing the stop ID of the parent station for the entrance.
     */
    STATION_ENTRANCE_EXIT
}