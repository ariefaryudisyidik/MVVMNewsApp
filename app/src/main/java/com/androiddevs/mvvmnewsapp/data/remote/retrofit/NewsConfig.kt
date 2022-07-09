package com.androiddevs.mvvmnewsapp.data.remote.retrofit

import com.androiddevs.mvvmnewsapp.BuildConfig
import com.androiddevs.mvvmnewsapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsConfig {
    fun getNewsApi(): NewsApi {
        val logger = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) setLevel(HttpLoggingInterceptor.Level.BODY)
            else setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }
}