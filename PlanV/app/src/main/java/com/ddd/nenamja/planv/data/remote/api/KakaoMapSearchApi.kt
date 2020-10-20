package com.ddd.nenamja.planv.data.remote.api

import com.ddd.nenamja.planv.BuildConfig
import com.ddd.nenamja.planv.data.remote.model.map.MapModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoMapSearchApi {

    @Headers(
        "Authorization: KakaoAK 3ac7572c0c395b06113084bec5772916"
    )
    @GET("address.json")
    suspend fun getMapData(
        @Query("query") query: String
    ): MapModel
}