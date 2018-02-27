package com.gmail.fattazzo.feedsources.transitland.rest

import com.gmail.fattazzo.feedsources.transitland.rest.api.FeedsService
import com.gmail.fattazzo.feedsources.transitland.rest.api.OperatorService
import com.gmail.fattazzo.feedsources.transitland.rest.api.RoutesService

object ApiRestCLient {

    private const val BASE_URL = "https://api.transit.land/"

    val operatorService: OperatorService
        get() = RetrofitClient.getClient(BASE_URL).create(OperatorService::class.java)

    val feedsService: FeedsService
        get() = RetrofitClient.getClient(BASE_URL).create(FeedsService::class.java)

    val routesService: RoutesService
        get() = RetrofitClient.getClient(BASE_URL).create(RoutesService::class.java)
}