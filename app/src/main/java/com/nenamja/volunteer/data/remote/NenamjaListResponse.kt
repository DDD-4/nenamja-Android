package com.nenamja.volunteer.data.remote

import com.nenamja.volunteer.data.Memo

/**
* api 호출 후 데이터 응답을 받는 클래스
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:18 PM
**/
data class NenamjaListResponse(
    var data: ArrayList<Memo?>? = ArrayList()
)
{
}