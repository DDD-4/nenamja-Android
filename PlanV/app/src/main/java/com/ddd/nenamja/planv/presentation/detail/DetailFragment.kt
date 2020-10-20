package com.ddd.nenamja.planv.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.ddd.nenamja.planv.R

class DetailFragment : Fragment(R.layout.fragment_detail) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("keyArg")
    }

}