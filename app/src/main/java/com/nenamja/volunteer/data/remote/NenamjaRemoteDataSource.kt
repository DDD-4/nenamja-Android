package com.nenamja.volunteer.data.remote

import android.content.Context
import com.nenamja.volunteer.net.NenamjaClient
import com.nenamja.volunteer.net.NenamjaResponse
import io.reactivex.Observable

/**
* 데이터 인터페이스를 구현한 구현체 객체
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:19 PM
**/
class NenamjaRemoteDataSource() {
    val nenamjaList: Observable<NenamjaResponse<NenamjaListResponse>>
        get() = NenamjaClient().provideService(NenamjaServiceApi::class.java)
            .memoList

}