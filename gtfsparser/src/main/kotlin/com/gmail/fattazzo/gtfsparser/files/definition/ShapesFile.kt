package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class ShapesFile(
        override val name: String = "shapes.txt",
        override val columnsHeader: Array<String> = arrayOf("shape_id", "shape_pt_lat", "shape_pt_lon", "shape_pt_sequence", "shape_dist_traveled"))
    : FileDefinition