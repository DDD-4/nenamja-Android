package com.nenamja.volunteer.data.remote.source

import com.nenamja.volunteer.data.remote.model.VolunteerListModel
import io.reactivex.Single

interface NenamjaRemoteDataSource : NenamjaDataSource {
    fun getVolunteerList(
        pageNo: Int? = 1,
        numOfRows: Int? = 10,
        sido: String?="",
        pgm: String?="",
        org: String?="",
        sdate: String?="",
        edate: String?="",
        today:String?
    ): Single<VolunteerListModel>
}