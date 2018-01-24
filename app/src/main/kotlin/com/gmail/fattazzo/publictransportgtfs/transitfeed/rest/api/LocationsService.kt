package com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.api

import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.LocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsService {

    @GET("getLocations")
    fun getLocations(@Query("key") key: String): Call<LocationResponse>
}