package com.nenamja.volunteer.data.remote.source

import com.nenamja.volunteer.data.remote.api.NenamjaServiceApi
import com.nenamja.volunteer.data.remote.model.VolunteerListModel
import io.reactivex.Single

class NenamjaRemoteDataSourceImpl(private val volunteerApi: NenamjaServiceApi) :
    NenamjaRemoteDataSource {
    override fun getVolunteerList(
        pageNo: Int?,
        numOfRows: Int?,
        sido: String?,
        pgm: String?,
        org: String?,
        sdate: String?,
        edate: String?,
        today: String?
    ): Single<VolunteerListModel> {
        return volunteerApi.getVolunteerList(
            pageNo = pageNo,
            rowNum = numOfRows,
            todayDate = today
        )
    }

}