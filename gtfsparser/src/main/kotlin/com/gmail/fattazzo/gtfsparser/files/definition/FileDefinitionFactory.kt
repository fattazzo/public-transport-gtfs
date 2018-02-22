package com.gmail.fattazzo.gtfsparser.files.definition

/**
 * @author fattazzo
 *         <p/>
 *         date: 21/02/18
 */
object FileDefinitionFactory {

    private val filesMap: Map<String, FileDefinition> by lazy {
        val agencyFile = AgencyFile()
        val stopsFile = StopsFile()
        val routesFile = RoutesFile()
        val tripsFile = TripsFile()
        val stopTimesFile = StopTimesFile()
        val calendarFile = CalendarFile()
        val calendarDatesFile = CalendarDatesFile()
        val fareAttributesFile = FareAttributesFile()
        val fareRulesFile = FareRulesFile()
        val shapesFile = ShapesFile()
        val frequenciesFile = FrequenciesFile()
        val transfersFile = TransfersFile()
        val feedInfoFile = FeedInfoFile()

        mapOf(Pair(agencyFile.name, agencyFile), Pair(stopsFile.name, stopsFile),
                Pair(routesFile.name, routesFile), Pair(tripsFile.name, tripsFile),
                Pair(stopTimesFile.name, stopTimesFile), Pair(calendarFile.name, calendarFile),
                Pair(calendarDatesFile.name, calendarDatesFile), Pair(fareAttributesFile.name, fareAttributesFile),
                Pair(fareRulesFile.name, fareRulesFile), Pair(shapesFile.name, shapesFile),
                Pair(frequenciesFile.name, frequenciesFile), Pair(transfersFile.name, transfersFile),
                Pair(feedInfoFile.name, feedInfoFile))
    }

    fun get(fileName: String): FileDefinition? {
        return filesMap[fileName]
    }
}