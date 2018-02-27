package com.gmail.fattazzo.feedsources.transitland

import android.content.Context
import com.gmail.fattazzo.feedsources.transitland.domain.Location
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext


/**
 * @author fattazzo
 *         <p/>
 *         date: 06/02/18
 */
@EBean(scope = EBean.Scope.Singleton)
open class LocationsManager {

    private val locationsMap: Map<String, List<Location>> by lazy {
        /**
         * Load all location from json file
         */
        val fileName = "locations.json"
        val jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val listType = object : TypeToken<ArrayList<Location>>() {
        }.type

        val locations = GsonBuilder().create().fromJson<List<Location>>(jsonString, listType)

        locations.groupBy({ it.countryCode }, { it })
    }

    private val emptyLocation: Location by lazy {
        val loc = Location()
        loc.code = ""
        loc.countryCode = ""
        loc.name = ""
        loc
    }

    @RootContext
    lateinit var context: Context

    /**
     * Get ISO3166-2 location by country code
     *
     * @param countryCode ISO two digits country code
     */
    fun getLocations(countryCode: String): List<Location> {
        val locations = mutableListOf(emptyLocation)
        locations.addAll(locationsMap[countryCode].orEmpty())
        return locations
    }

    fun getLocationByCode(code: String): Location {
        val countryLocations = getLocations(code.split('-')[0])
        val locMap: Map<String, Location> = countryLocations.map { it.code to it }.toMap()
        return locMap[code] ?: emptyLocation
    }
}