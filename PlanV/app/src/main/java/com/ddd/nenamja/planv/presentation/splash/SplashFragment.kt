package com.ddd.nenamja.planv.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ddd.nenamja.planv.R
import org.koin.android.ext.android.inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startSplash(this)
    }

    fun goToHome() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

}