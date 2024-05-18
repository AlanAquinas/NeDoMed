package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class AnalysResponse(
    val id: Int,
    val date: String,
    val conclusion: String,
    @SerializedName("test_type") val testType: String,
    @SerializedName("analysis_details") val analysisDetails: String,
    val patient: Int,
    val doctor: Int
)