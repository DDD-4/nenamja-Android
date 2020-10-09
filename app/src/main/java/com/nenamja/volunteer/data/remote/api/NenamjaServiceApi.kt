package com.nenamja.volunteer.data.remote.api

import com.nenamja.volunteer.BuildConfig
import com.nenamja.volunteer.data.remote.model.VolunteerListModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NenamjaServiceApi {
    @GET("/mainVolList")
    fun getVolunteerList(
        @Query("query") pageNo: Int? = 1,
        @Query("query") rowNum: Int? = 10,
        @Query("query") todayDate: String?
    ): Single<VolunteerListModel>
}