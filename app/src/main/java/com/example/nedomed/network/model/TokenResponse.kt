package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("refresh") val refresh_token: String,
    @SerializedName("access") val access_token: String
)