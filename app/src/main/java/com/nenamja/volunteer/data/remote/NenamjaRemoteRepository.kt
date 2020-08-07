package com.nenamja.volunteer.data.remote

import io.reactivex.Flowable

interface NenamjaRemoteRepository : NenamjaRepository {
    fun getVolunteerList(
        pageNo: Int? = 1,
        numOfRows: Int? = 10,
        sido: String? = "",
        pgm: String? = "",
        org: String? = "",
        sdate: String? = "",
        edate: String? = ""
    ): Flowable<List<Any>>
}