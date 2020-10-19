package com.ddd.nenamja.planv.di

import com.ddd.nenamja.planv.BuildConfig
import com.ddd.nenamja.planv.data.remote.api.VolunteerDataApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jaxb.JaxbConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    single { HttpLoggingInterceptor() }

    single {
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { JaxbConverterFactory.create() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(get<JaxbConverterFactory>())
            .build()
    }

    single { get<Retrofit>().create(VolunteerDataApi::class.java) }

//    single<MetaWeatherDataSource> { MetaWeatherDataSourceImpl(get()) }

}