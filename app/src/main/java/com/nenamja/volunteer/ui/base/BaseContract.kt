package com.nenamja.volunteer.ui.base

interface BaseContract {
    interface View

    interface Presenter<in VIEW : View> {
        fun attachView(view: VIEW)
        fun detachView()
        fun subscribe()
        fun unsubscribe()
        fun clearDisposable()
    }
}