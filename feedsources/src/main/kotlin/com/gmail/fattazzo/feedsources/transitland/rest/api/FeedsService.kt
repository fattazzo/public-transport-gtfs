package com.gmail.fattazzo.feedsources.transitland.rest.api

import com.gmail.fattazzo.feedsources.transitland.domain.Feed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
interface FeedsService {

    @GET("api/v1/feeds/{id}")
    fun getFeed(@Path("id") feedId: String): Call<Feed>
}