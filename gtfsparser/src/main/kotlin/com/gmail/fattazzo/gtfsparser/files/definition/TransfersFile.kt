package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
class TransfersFile(
        override val name: String = "transfers.txt",
        override val columnsHeader: Array<String> = arrayOf("from_stop_id", "to_stop_id", "transfer_type", "min_transfer_time"))
    : FileDefinition