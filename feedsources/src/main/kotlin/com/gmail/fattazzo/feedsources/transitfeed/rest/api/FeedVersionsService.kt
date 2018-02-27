package com.gmail.fattazzo.feedsources.transitfeed.rest.api

import com.gmail.fattazzo.feedsources.transitfeed.Config
import com.gmail.fattazzo.feedsources.transitfeed.domain.response.FeedVersionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
interface FeedVersionsService {

    @GET("getFeedVersions")
    fun getFeedVersions(@Query("key") key: String = Config.key,
                        @Query("feed") feed: String,
                        @Query("page") page: Int = 1,
                        @Query("limit") limit: Int = 10,
                        @Query("err") err: Int = 0,
                        @Query("warn") warn: Int = 0): Call<FeedVersionResponse>
}