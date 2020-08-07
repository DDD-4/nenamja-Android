package com.nenamja.volunteer.ui.home

import com.nenamja.volunteer.App
import com.nenamja.volunteer.data.remote.NenamjaRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
* HomePresenter
* @AUTHOR 박기완
* @VERSION 
* @DATE 2020/07/25 1:24 PM
**/
class HomePresenter(view:HomeFragment) : HomeContract.PresenterForFragment {

    var mView: HomeContract.ViewForFragment = view
    var mDataSource: NenamjaRemoteDataSource
    var mCompositeDisposable: CompositeDisposable

    init {
        mDataSource = NenamjaRemoteDataSource(mView.getCurrentActivity())
        mCompositeDisposable = CompositeDisposable()
        mView.setPresenter(this)
    }

    override fun saveMemo(memoId: String?, contents: String?) {
        TODO("Not yet implemented")
    }

    override fun loadMemoList(keyword: String?) {
        App.dlog.e(
            (keyword == null || keyword == "").toString() + " " + keyword
        )
        if (keyword == null || keyword == "") {
            mDataSource?.nenamjaList
                ?.observeOn(AndroidSchedulers.mainThread())?.doOnSubscribe { disposable ->
                    mView!!.showProgress()
                }?.doFinally {
                    mView!!.dismissProgress()
                }?.subscribe( //new Consumer<SoeResponse<List<Memo>>>() {
                    //onNext
                    { response ->
                        App.dlog.e(response.getResult()?.data?.size.toString() + "")
                        mView!!.updateMemoList("", response?.getResult()?.data)
                    },  //onError
                    { throwable -> throwable.printStackTrace() }
                )?.let {
                    mCompositeDisposable!!.add(
                        it
                    )
                }
        } else {

        }
    }

    override fun remove(memoId: String?, position: Int) {
        //TODO("Not yet implemented")
    }

    override fun search(keyword: String?) {
        //TODO("Not yet implemented")
    }

    override fun subscribe() {
       // TODO("Not yet implemented")
    }

    override fun unsubscribe() {
       // TODO("Not yet implemented")
    }
}