package com.nenamja.volunteer.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nenamja.volunteer.R
import com.nenamja.volunteer.data.remote.model.VolunteerContent
import com.nenamja.volunteer.ui.base.BaseFragment
import com.nenamja.volunteer.ui.home.adapter.VolunteerListAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * HomeView
 * @AUTHOR 박기완
 * @VERSION
 * @DATE 2020/07/25 1:24 PM
 **/
class HomeFragment :
    BaseFragment<HomeContract.ViewForFragment, HomeContract.PresenterForFragment>(R.layout.fragment_home),
    HomeContract.ViewForFragment {

//    private var mPresenter: HomeContract.PresenterForFragment? = null

    override val presenter = HomePresenter()

    override fun doViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO : ㄱㅣ존의 View Creted에서 하는 작업을 넣으면 됩니다. 레이아웃 초기화등
        with(rv_list) {
            adapter = VolunteerListAdapter()
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        loadMemoList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
//        HomePresenter(this)
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun getCurrentActivity(): Context {
        return activity as Context
    }

    override fun showProgress() {
        //TODO("Not yet implemented")
    }

    override fun dismissProgress() {
        // TODO("Not yet implemented")
    }

    override fun loadMemoList() {
        presenter.loadMemoList("")
    }

    override fun updateVolunteerList(keyword: String?, volunteerList: List<VolunteerContent>) {
        //TODO("Not yet implemented")
        rv_list?.let {
            (it.adapter as VolunteerListAdapter).setVolunteerList(volunteerList)
        }
    }

    override fun searchViewOn() {
        // TODO("Not yet implemented")
    }

    override fun drawLineAtScrollDown(isDraw: Boolean) {
        //TODO("Not yet implemented")
    }

    override fun updateMemoListAfterRemove(message: String?, memoId: String?) {
        //TODO("Not yet implemented")
    }

}