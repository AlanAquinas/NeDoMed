package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    val username : String,
    val password :String,
    val email:String,
    @SerializedName("user_type")
    val userType:String
)

data class SignUpResponse(
    val id : Int,
    val username: String,
    val email: String,
    @SerializedName("user_type")
    val userType: String
)
