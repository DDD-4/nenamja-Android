package com.nenamja.volunteer.data.remote

import com.nenamja.volunteer.data.remote.model.VolunteerListModel
import io.reactivex.Flowable

interface NenamjaRemoteRepository : NenamjaRepository {
    fun getVolunteerList(
        pageNo: Int? = 1,
        numOfRows: Int? = 10,
        sido: String? = "",
        pgm: String? = "",
        org: String? = "",
        sdate: String? = "",
        edate: String? = "",
        today: String?
    ): Flowable<VolunteerListModel>
}