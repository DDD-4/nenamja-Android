package com.nenamja.volunteer.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nenamja.volunteer.R
import com.nenamja.volunteer.data.Memo

/**
 * HomeView
 * @AUTHOR 박기완
 * @VERSION
 * @DATE 2020/07/25 1:24 PM
 **/
class HomeFragment : Fragment(), HomeContract.ViewForFragment {

    private var mPresenter: HomeContract.PresenterForFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        HomePresenter(this)
        mPresenter?.subscribe()
    }

    override fun onPause() {
        super.onPause()
        mPresenter?.unsubscribe()
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
        mPresenter!!.loadMemoList("")
    }

    override fun updateMemoList(keyword: String?, memoList: ArrayList<Memo?>?) {
        //TODO("Not yet implemented")
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

    override fun setPresenter(presenter: HomeContract.PresenterForFragment?) {
        assert(presenter != null)
        mPresenter = presenter
    }
}