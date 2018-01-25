package com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.api

import com.gmail.fattazzo.publictransportgtfs.transitfeed.Config
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.response.FeedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
interface FeedsService {

    @GET("getFeeds")
    fun getFeeds(@Query("key") key: String = Config.key,
                 @Query("location") locationId: Int,
                 @Query("descendants") descendants: Int = 1,
                 @Query("page") page: Int = 1,
                 @Query("limit") limit: Int = 10,
                 @Query("type") type: String = "gtfs"): Call<FeedResponse>
}