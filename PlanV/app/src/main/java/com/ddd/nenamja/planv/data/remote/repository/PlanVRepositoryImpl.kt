package com.ddd.nenamja.planv.data.remote.repository


import com.ddd.nenamja.planv.data.remote.source.PlanVDataSource

class PlanVRepositoryImpl(private val planVDataSource: PlanVDataSource):PlanVRepository {
    override suspend fun getVolunteerList(
        page: Int,
        date: String,
        location: String
    ): String {
        return planVDataSource.getVolunteerList(
            page = page,
            date = date,
            location = location
        )
    }

    override suspend fun getVolunteerDetail() {
    }
}