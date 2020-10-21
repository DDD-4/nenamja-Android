package com.ddd.nenamja.planv.di

import com.ddd.nenamja.planv.presentation.detail.DetailViewModel
import com.ddd.nenamja.planv.presentation.home.HomeViewModel
import com.ddd.nenamja.planv.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { SplashViewModel() }

    viewModel { HomeViewModel(get()) }

    viewModel { DetailViewModel(get(), get()) }

}