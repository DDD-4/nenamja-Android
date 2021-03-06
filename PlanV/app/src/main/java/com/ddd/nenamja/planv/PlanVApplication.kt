package com.ddd.nenamja.planv

import android.app.Application
import android.util.Log
import com.ddd.nenamja.planv.di.remoteModule
import com.ddd.nenamja.planv.di.repositoryModule
import com.ddd.nenamja.planv.di.viewModelModule
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.navi.NaviClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PlanVApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "afccb3e4d2081161b4a1570ed23a0ea7")
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@PlanVApplication)
            modules(
                remoteModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}