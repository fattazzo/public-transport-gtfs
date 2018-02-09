package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.rest

import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.rest.api.FeedsService
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.rest.api.OperatorService

object ApiRestCLient {

    private const val BASE_URL = "https://api.transit.land/"

    val operatorService: OperatorService
        get() = RetrofitClient.getClient(BASE_URL).create(OperatorService::class.java)

    val feedsService: FeedsService
        get() = RetrofitClient.getClient(BASE_URL).create(FeedsService::class.java)
}