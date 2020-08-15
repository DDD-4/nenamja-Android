package com.nenamja.volunteer.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.nenamja.volunteer.App.Companion.dlog

/**
* 최상위 프레젠터 객체
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:20 PM
**/

abstract class BasePresenter<VIEW : BaseContract.View> :
    BaseContract.Presenter<VIEW> {
    protected lateinit var view: VIEW
        private set

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    /**
     *
     */
    override fun subscribe(){

    }

    /**
     *
     */
    override fun unsubscribe(){

    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

}