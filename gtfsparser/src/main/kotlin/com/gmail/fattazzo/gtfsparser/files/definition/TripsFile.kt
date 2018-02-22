package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class TripsFile(
        override val name: String = "trips.txt",
        override val columnsHeader: Array<String> = arrayOf("route_id", "service_id", "trip_id", "trip_headsign", "trip_short_name", "direction_id", "block_id", "shape_id", "wheelchair_accessible", "bikes_allowed"))
    : FileDefinition