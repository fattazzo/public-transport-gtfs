package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland

import android.content.Context
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Location
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

    private var locationsMap: Map<String, List<Location>>? = null
        get() {
            if (field == null) {
                loadLocations()
            }
            return field
        }

    @RootContext
    lateinit var context: Context

    /**
     * Get ISO3166-2 location by country code
     *
     * @param countryCode ISO two digits country code
     */
    fun getLocations(countryCode: String): List<Location> {
        return locationsMap?.get(countryCode).orEmpty()
    }

    /**
     * Load all location from json file
     */
    private fun loadLocations() {
        val fileName = "locations.json"
        val jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val listType = object : TypeToken<ArrayList<Location>>() {
        }.type

        val locations = GsonBuilder().create().fromJson<List<Location>>(jsonString, listType)

        locationsMap = locations.groupBy({ it.countryCode }, { it })
    }
}