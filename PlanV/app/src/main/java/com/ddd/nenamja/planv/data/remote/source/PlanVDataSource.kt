package com.ddd.nenamja.planv.data.remote.source

interface PlanVDataSource {
    suspend fun getVolunteerList(page: Int, date: String, location: String): String
    suspend fun getVolunteerDetail()
}