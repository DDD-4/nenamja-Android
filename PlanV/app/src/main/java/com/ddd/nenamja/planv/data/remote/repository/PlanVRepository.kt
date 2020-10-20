package com.ddd.nenamja.planv.data.remote.repository

interface PlanVRepository {
    suspend fun getVolunteerList(page: Int, date: String, location: String): String
    suspend fun getVolunteerDetail(key: String): String
}