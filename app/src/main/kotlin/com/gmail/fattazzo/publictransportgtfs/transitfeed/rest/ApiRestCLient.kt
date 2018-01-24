package com.gmail.fattazzo.publictransportgtfs.transitfeed.rest

import com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.api.LocationsService

object ApiRestCLient {

    val BASE_URL = "https://api.transitfeeds.com/v1/"

    val locationsService: LocationsService
        get() = RetrofitClient.getClient(BASE_URL).create(LocationsService::class.java)
}