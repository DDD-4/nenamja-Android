package com.nenamja.volunteer.`interface`

/**
* 최상위 뷰 객체
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:20 PM
**/
interface BaseView<T> {
    /**
     * 뷰와 연관된 Presenter 를 등록한다.
     * @param presenter
     */
    fun setPresenter(presenter: T)
}