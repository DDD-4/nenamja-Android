package com.nenamja.volunteer.data.remote.source

import com.nenamja.volunteer.data.remote.api.NenamjaServiceApi
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
        edate: String?
    ): Single<List<Any>> {
        return volunteerApi.getVolunteerList(
            pageNo = pageNo,
            numOfRows = numOfRows,
            sido = sido,
            pgm = pgm,
            org = org,
            sdate = sdate,
            edate = edate
        )
    }

}