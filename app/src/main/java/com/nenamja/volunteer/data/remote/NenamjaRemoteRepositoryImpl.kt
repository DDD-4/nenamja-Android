package com.nenamja.volunteer.data.remote

import com.nenamja.volunteer.data.local.NenamjaLocalDataSourceImpl
import com.nenamja.volunteer.data.remote.source.NenamjaRemoteDataSource
import io.reactivex.Flowable

class NenamjaRemoteRepositoryImpl(
    private val nenamjaRemoteDataSource: NenamjaRemoteDataSource,
    private val nenamjaLocalDataSourceImpl: NenamjaLocalDataSourceImpl
) : NenamjaRemoteRepository {
    override fun getVolunteerList(
        pageNo: Int?,
        numOfRows: Int?,
        sido: String?,
        pgm: String?,
        org: String?,
        sdate: String?,
        edate: String?
    ): Flowable<List<Any>> {
        return nenamjaRemoteDataSource.getVolunteerList(
            pageNo,
            numOfRows,
            sido,
            pgm,
            org,
            sdate,
            edate
        ).onErrorReturn { listOf() }.toFlowable()
    }
}