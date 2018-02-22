package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class RoutesFile(
        override val name: String = "routes.txt",
        override val columnsHeader: Array<String> = arrayOf("route_id", "agency_id", "route_short_name", "route_long_name", "route_desc", "route_type", "route_url", "route_color", "route_text_color", "route_sort_order"))
    : FileDefinition