package com.ddd.nenamja.planv.data.remote.source

import com.ddd.nenamja.planv.data.remote.api.VolunteerDataApi

class PlanVDataSourceImpl(private val volunteerDataApi: VolunteerDataApi):PlanVDataSource {
    override suspend fun getVolunteerList(
        page: Int,
        date: String,
        location: String
    ): String {
        return volunteerDataApi.getVolunteerList(
            page = page,
            edate = date,
            sido = location
        )
    }

    override suspend fun getVolunteerDetail(key: String): String {
        return volunteerDataApi.getVolunteerDetail(key = key)
    }
}