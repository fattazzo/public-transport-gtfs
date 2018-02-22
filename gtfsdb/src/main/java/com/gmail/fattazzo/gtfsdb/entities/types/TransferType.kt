package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 24/01/18
 */
enum class TransferType {

    /**
     * This is a recommended transfer point between routes.
     */
    RECOMMENDED,

    /**
     * This is a timed transfer point between two routes. The departing vehicle is expected to wait for the arriving one, with sufficient time for a passenger to transfer between routes.
     */
    TIMED,

    /**
     * This transfer requires a minimum amount of time between arrival and departure to ensure a connection. The time required to transfer is specified by min_transfer_time.
     */
    MIN_AMOUNT_OF_TIME,

    /**
     * Transfers are not possible between routes at this location.
     */
    NOT_POSSIBLE
}