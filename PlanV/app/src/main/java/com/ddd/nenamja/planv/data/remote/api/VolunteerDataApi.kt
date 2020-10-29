package com.ddd.nenamja.planv.data.remote.api

import com.ddd.nenamja.planv.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VolunteerDataApi {
    //서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주
    @GET("getVolProgrmList")
    suspend fun getVolunteerList(
        @Query("serviceKey") serviceKey: String = BuildConfig.AUTH_KEY,
        @Query("pageNo") page: Int = 1,
        @Query("numOfRows") numOfRows: Int = 20,
        @Query("sido") sido: String = "",
        @Query("edate") edate: String
    ): String

    @GET("getVolProgrmInfo")
    suspend fun getVolunteerDetail(
        @Query("serviceKey") serviceKey: String = BuildConfig.AUTH_KEY,
        @Query("key1") key: String
    ): String
}