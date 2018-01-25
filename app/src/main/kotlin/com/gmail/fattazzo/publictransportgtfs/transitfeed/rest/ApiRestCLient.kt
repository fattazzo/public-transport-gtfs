package com.gmail.fattazzo.publictransportgtfs.transitfeed.rest

import com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.api.FeedVersionsService
import com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.api.FeedsService
import com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.api.LocationsService

object ApiRestCLient {

    private const val BASE_URL = "https://api.transitfeeds.com/v1/"

    val locationsService: LocationsService
        get() = RetrofitClient.getClient(BASE_URL).create(LocationsService::class.java)

    val feedsService: FeedsService
        get() = RetrofitClient.getClient(BASE_URL).create(FeedsService::class.java)

    val feedVersionsService: FeedVersionsService
        get() = RetrofitClient.getClient(BASE_URL).create(FeedVersionsService::class.java)
}