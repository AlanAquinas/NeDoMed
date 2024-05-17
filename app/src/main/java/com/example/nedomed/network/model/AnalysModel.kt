package com.example.nedomed.network.model

data class AnalysResponse(
    val id: Int,
    val date: String,
    val conclusion: String,
    val testType: String,
    val analysisDetails: String,
    val patient: Int,
    val doctor: Int
)