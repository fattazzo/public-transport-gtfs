package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.rest.api

import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.Config
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitfeed.domain.response.LocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsService {

    @GET("getLocations")
    fun getLocations(@Query("key") key: String = Config.key): Call<LocationResponse>
}