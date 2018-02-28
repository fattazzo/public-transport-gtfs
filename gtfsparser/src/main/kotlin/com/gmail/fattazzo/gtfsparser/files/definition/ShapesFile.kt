package com.gmail.fattazzo.gtfsparser.files.definition

import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.NUMBER
import com.gmail.fattazzo.gtfsparser.files.processor.datatype.DataType.STRING

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class ShapesFile(
        override val name: String = "shapes.txt",
        override val columnsHeader: Array<String> = arrayOf("shape_id", "shape_pt_lat", "shape_pt_lon", "shape_pt_sequence", "shape_dist_traveled"),
        override val columnsType: Array<DataType> = arrayOf(STRING, NUMBER, NUMBER, STRING, STRING))
    : FileDefinition