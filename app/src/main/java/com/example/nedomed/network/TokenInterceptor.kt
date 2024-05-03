package com.example.nedomed.network

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val preferences: SharedPreferences): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.getString("token", null).orEmpty()
        val request = chain.request().newBuilder().addHeader("Authorization", token).build()
        return chain.proceed(request)
    }
}