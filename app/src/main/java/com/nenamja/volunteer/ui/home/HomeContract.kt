package com.nenamja.volunteer.ui.home

import android.content.Context
import com.nenamja.volunteer.ui.base.BaseContract
import com.nenamja.volunteer.data.Memo

/**
 * Home View 와 Presenter
 * @AUTHOR 박기완
 * @VERSION
 * @DATE 2020/07/25 1:24 PM
 **/

interface HomeContract {

    /**
     * Home 뷰 화면과 관련된 presenter interface.
     */
    interface PresenterForFragment : BaseContract.Presenter<ViewForFragment> {

        /**
         *
         */
        override fun subscribe()

        /**
         *
         */
        override fun unsubscribe()


        /**
         * 메모 저장 함수
         * @param memoId
         * @param contents
         */
        fun saveMemo(memoId: String?, contents: String?)

        /**
         * 메모 목록 불러오기. 키워드 삽입시, search
         */
        fun loadMemoList(keyword: String?)

        /**
         * 메모 삭제
         */
        fun remove(memoId: String?, position: Int)

        /**
         * 메모 검색
         */
        fun search(keyword: String?)
    }

    /**
     * 메인 뷰 interface
     */
    interface ViewForFragment : BaseContract.View {

        /**
         * 현재 뷰(프레그먼트)가 속한 엑티비티를 반환한
         */
        fun getCurrentActivity(): Context

        /**
         * 진행바를 표시 한다.
         */
        fun showProgress()

        /**
         * 진행바를 감춘다.
         */
        fun dismissProgress()

        /**
         * 메모 목록 불러오기 API 호출 명령을 presenter로 전달한다
         * @param memoList
         */
        fun loadMemoList()

        /**
         * 메모 목록을 화면상에 업데이트 한다.
         * @param memoList
         */
        fun updateVolunteerList(
            keyword: String?,
            memoList: List<Any>
        )

        /**
         * 메모 검색 화면을 띄운다
         */
        fun searchViewOn()

        /**
         * 타이틀과 컨텐츠 사이에 라인을 그림
         * @param isDraw
         */
        fun drawLineAtScrollDown(isDraw: Boolean)

        /**
         * 메모를 지운 후에 메모 목록을 업데이트 한다
         * @param message
         * @param memoId
         */
        fun updateMemoListAfterRemove(
            message: String?,
            memoId: String?
        )

    }

}