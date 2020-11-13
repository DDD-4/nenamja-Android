package com.ddd.nenamja.planv.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    fun startSplash(view : SplashFragment){
        viewModelScope.launch {
            delay(2500)
            view.goToHome()
        }
    }
}