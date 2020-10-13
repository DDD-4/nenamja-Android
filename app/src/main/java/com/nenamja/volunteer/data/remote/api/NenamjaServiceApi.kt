package com.nenamja.volunteer.data.remote.api

import com.nenamja.volunteer.BuildConfig
import com.nenamja.volunteer.data.remote.model.VolunteerListModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NenamjaServiceApi {
    @GET("mainVolList")
    fun getVolunteerList(
        @Query("page") pageNo: Int? = 1,
        @Query("rowNum") rowNum: Int? = 15,
        @Query("todayDate") todayDate: String?
    ): Single<VolunteerListModel>
}