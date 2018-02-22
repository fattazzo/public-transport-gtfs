package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.rest.api

import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.Operator
import com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain.response.OperatorResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author fattazzo
 *         <p/>
 *         date: 25/01/18
 */
interface OperatorService {

    @GET("api/v1/operators")
    fun getOperators(@Query("sort_key") sortKey: String = "name",
                     @Query("sort_order") sortOrder: String = "asc",
                     @Query("per_page") perPage: Int = 50,
                     @Query("offset") offset: Int = 0,
                     @QueryMap(encoded = true) options: Map<String, String>): Call<OperatorResponse>

    @GET("api/v1/onestop_id/{id}")
    fun loadOpetaror(@Path("id") operatorId: String): Call<Operator>
}