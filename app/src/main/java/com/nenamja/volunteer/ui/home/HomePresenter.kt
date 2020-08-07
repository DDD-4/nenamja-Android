package com.nenamja.volunteer.ui.home

import com.nenamja.volunteer.App
import com.nenamja.volunteer.ui.base.BasePresenter
import com.nenamja.volunteer.data.remote.NenamjaRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * HomePresenter
 * @AUTHOR 박기완
 * @VERSION
 * @DATE 2020/07/25 1:24 PM
 **/
class HomePresenter : BasePresenter<HomeContract.ViewForFragment>(),
    HomeContract.PresenterForFragment {

    private val mDataSource: NenamjaRemoteDataSource = NenamjaRemoteDataSource()

    override fun saveMemo(memoId: String?, contents: String?) {
        //TODO("Not yet implemented")
    }

    override fun loadMemoList(keyword: String?) {
        App.dlog.e(
            (keyword == null || keyword == "").toString() + " " + keyword
        )
        keyword?.let { searchKeyword ->
            mDataSource.nenamjaList
                .observeOn(AndroidSchedulers.mainThread())?.doOnSubscribe { disposable ->
                    view.showProgress()
                }?.doFinally {
                    view.dismissProgress()
                }?.subscribe( //new Consumer<SoeResponse<List<Memo>>>() {
                    //onNext
                    { response ->
                        App.dlog.e(response.getResult()?.data?.size.toString() + "")
                        view.updateMemoList(searchKeyword, response?.getResult()?.data)
                    },  //onError
                    { throwable -> throwable.printStackTrace() }
                )?.addDisposable()
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