package com.ddd.nenamja.planv.di

import com.ddd.nenamja.planv.BuildConfig
import com.ddd.nenamja.planv.data.remote.api.KakaoMapSearchApi
import com.ddd.nenamja.planv.data.remote.api.VolunteerDataApi
import com.ddd.nenamja.planv.data.remote.source.MapDataSource
import com.ddd.nenamja.planv.data.remote.source.MapDataSourceImpl
import com.ddd.nenamja.planv.data.remote.source.PlanVDataSource
import com.ddd.nenamja.planv.data.remote.source.PlanVDataSourceImpl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
private const val GSON_DATE_FORMAT = "E, dd MMM yyyy HH:mm:ss Z"
val remoteModule = module {

    single { HttpLoggingInterceptor() }

    single {
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { ScalarsConverterFactory.create() }

    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(get<ScalarsConverterFactory>())
            .build().create(VolunteerDataApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.MAP_BASE_URL)
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .build().create(KakaoMapSearchApi::class.java)
    }

//    single { get<Retrofit>().create(VolunteerDataApi::class.java) }
//
//    single { get<Retrofit>().create(KakaoMapSearchApi::class.java) }

    single<PlanVDataSource> { PlanVDataSourceImpl(get()) }

    single<MapDataSource> { MapDataSourceImpl(get()) }

}