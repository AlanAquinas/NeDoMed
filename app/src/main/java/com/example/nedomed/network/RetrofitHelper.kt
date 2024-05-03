package com.example.nedomed.network

import android.content.Context
import android.content.SharedPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {

    fun getApi(context: Context) = getRetrofit(context).create<API>()

    fun getRetrofit(context: Context):Retrofit{
        val preferences = context.getSharedPreferences("default", Context.MODE_PRIVATE)
        val interceptor = getInterceptor(preferences)
        val okHttpClient = getOKHTTPClient(interceptor)
        return Retrofit.Builder().baseUrl("https://nedomed-backend.onrender.com").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    fun getInterceptor(preferences: SharedPreferences) = TokenInterceptor(preferences)

    fun getOKHTTPClient(interceptor: TokenInterceptor): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}