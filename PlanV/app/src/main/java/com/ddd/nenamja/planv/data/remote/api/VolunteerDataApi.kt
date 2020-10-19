package com.ddd.nenamja.planv.data.remote.api

import com.ddd.nenamja.planv.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VolunteerDataApi {
    @GET("getVolProgrmList")
    suspend fun getVolunteerList(
        @Query("serviceKey") serviceKey: String = BuildConfig.AUTH_KEY,
        @Query("pageNo") page: Int = 1,
        @Query("numOfRows") numOfRows: Int = 20,
        @Query("sido") sido: String = "",
        @Query("edate") edate: String
    ): String

    @GET("api/location/{locationId}/")
    suspend fun getVolunteerDetail(@Path("locationId") locationId: Int): Any
}