package com.nenamja.volunteer.net

import android.content.Context
import com.nenamja.volunteer.BuildConfig
import com.nenamja.volunteer.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
* retrofit 객체
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:20 PM
**/
class NenamjaClient {

    companion object {
        private const val CONNECT_TIMEOUT = 10000
        private const val READ_TIMEOUT = 10000
        private const val CONTENT_TYPE = "application/json; charset=utf-8"
        private const val CHANNEL_ID = "HD"
    }


    fun <T> provideService(service: Class<T>?): T {
        val ret =
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(provideHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create()).build()
        return ret.create(service)
    }

    private fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val level = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().connectTimeout(
            10000L,
            TimeUnit.MILLISECONDS
        ).readTimeout(10000L, TimeUnit.MILLISECONDS).addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("x-channel-id", "HD").build()
            val response = it.proceed(request)
            response
        }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}