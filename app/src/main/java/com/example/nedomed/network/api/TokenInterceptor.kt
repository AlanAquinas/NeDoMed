package com.example.nedomed.network.api

import com.example.nedomed.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(): Interceptor {
    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = tokenManager.getToken()
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzE3MTQxMDMxLCJpYXQiOjE3MTU4NDUwMzEsImp0aSI6IjA5Yzk1NmZiODE4NzRhMzc5OTcxZjdjNGJhOTIzNDM0IiwidXNlcl9pZCI6NX0.HeZTBSPulrP6yOMIyhyvzvLQg32jMQwwogc2DlSHQuo")
        return chain.proceed(request.build())
    }
}