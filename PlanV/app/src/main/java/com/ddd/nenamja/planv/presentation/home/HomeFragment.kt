package com.ddd.nenamja.planv.presentation.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ddd.nenamja.planv.R
import com.ddd.nenamja.planv.presentation.splash.SplashViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.statusBarColor = resources.getColor(R.color.colorStatusBar, null)
        } else {
            requireActivity().window.statusBarColor = resources.getColor(R.color.colorStatusBar)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoading ->
            pb_loading.visibility = if(isLoading == true) View.VISIBLE else View.GONE
        })
        viewModel.refreshing.observe(viewLifecycleOwner, Observer { refresh ->
            pb_loading.visibility = if(refresh == true) View.VISIBLE else View.GONE
        })
        viewModel.volunteerDataList.observe(viewLifecycleOwner, Observer { volunteerList ->
            Log.d("ironelder", "list = $volunteerList")
        })
    }

}