package com.nenamja.volunteer.data.remote

import com.nenamja.volunteer.data.local.NenamjaLocalDataSourceImpl
import com.nenamja.volunteer.data.remote.model.VolunteerListModel
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
        edate: String?,
        today: String?
    ): Flowable<VolunteerListModel> {
        return nenamjaRemoteDataSource.getVolunteerList(
            pageNo = pageNo,
            numOfRows = numOfRows,
            today = today
        ).onErrorReturn { VolunteerListModel(result = "400", contents = listOf()) }.toFlowable()
    }
}