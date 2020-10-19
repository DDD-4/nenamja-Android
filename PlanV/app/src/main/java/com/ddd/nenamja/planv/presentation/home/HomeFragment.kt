package com.ddd.nenamja.planv.presentation.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ddd.nenamja.planv.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.statusBarColor = resources.getColor(R.color.colorStatusBar, null)
        } else {
            requireActivity().window.statusBarColor = resources.getColor(R.color.colorStatusBar)
        }
    }

}