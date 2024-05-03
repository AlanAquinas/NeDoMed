package com.example.nedomed.network

import android.content.SharedPreferences
import com.example.nedomed.network.model.SignInModel
import com.example.nedomed.network.model.SignUpModel
import com.example.nedomed.network.model.SignUpResponse

class AuthRepository (
    private val preferences: SharedPreferences,
    private val api: API
) {

    suspend fun login(username: String, password: String){
        api.signIn(SignInModel(username, password)).let { preferences.edit().putString("token", it).commit() }
    }

    suspend fun signUp(username: String, email: String, password: String, userType: String): SignUpResponse{
        val request = SignUpModel(username = username, email = email, password = password, userType = userType)
        return api.signUp(request)
    }
}