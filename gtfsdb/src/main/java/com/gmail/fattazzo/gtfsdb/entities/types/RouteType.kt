package com.gmail.fattazzo.gtfsdb.entities.types

/**
 * @author fattazzo
 *         <p/>
 *         date: 23/01/18
 */
enum class RouteType {

    /**
     * Tram, Streetcar, Light rail. Any light rail or street level system within a metropolitan area.
     */
    TRAM_STREETCAR_LIGHTRAIL,

    /**
     * Subway, Metro. Any underground rail system within a metropolitan area.
     */
    SUBWAY_METRO,

    /**
     * Rail. Used for intercity or long-distance travel.
     */
    RAIL,

    /**
     * Bus. Used for short- and long-distance bus routes.
     */
    BUS,

    /**
     * Ferry. Used for short- and long-distance boat service.
     */
    FERRY,

    /**
     * Cable car. Used for street-level cable cars where the cable runs beneath the car.
     */
    CABLE_CAR,

    /**
     * Gondola, Suspended cable car. Typically used for aerial cable cars where the car is suspended from the cable.
     */
    GONDOLA_SUSPENDEDCABLECAR,

    /**
     * Funicular. Any rail system designed for steep inclines.
     */
    FUNICULAR
}