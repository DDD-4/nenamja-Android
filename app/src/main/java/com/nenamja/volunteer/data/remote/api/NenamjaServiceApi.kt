package com.nenamja.volunteer.data.remote.api

import com.nenamja.volunteer.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NenamjaServiceApi {
    @GET("getVolProgrmList")
    fun getVolunteerList(
        @Query("query") serviceKey: String = BuildConfig.AUTH_KEY,
        @Query("query") pageNo: Int? = 1,
        @Query("query") numOfRows: Int? = 10,
        @Query("query") sido: String?,
        @Query("query") pgm: String?,
        @Query("query") org: String?,
        @Query("query") sdate: String?,
        @Query("query") edate: String?
    ): Single<List<Any>>
}