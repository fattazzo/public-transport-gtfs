package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.rest.api

import com.gmail.fattazzo.publictransportgtfs.feeds.source.GeoJsonFeatures
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
interface RoutesService {

    @GET("api/v1/routes.geojson")
    fun getGeoJson(@Query("operated_by") idOperatore: String,
                   @Query("per_page") perPage: Int = 1000): Call<GeoJsonFeatures>
}