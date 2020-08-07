package com.nenamja.volunteer.ui.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

abstract class BaseFragment<in VIEW : BaseContract.View, PRESENTER : BaseContract.Presenter<VIEW>>(
    private val mLayoutResId: Int
) : Fragment(), BaseContract.View {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.attachView(this as VIEW)
        return inflater.inflate(mLayoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    abstract val presenter: PRESENTER
    abstract fun doViewCreated(view: View, savedInstanceState: Bundle?)
}