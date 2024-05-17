package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class PatientResponse(
    @SerializedName("user")
    val userId: Int,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("blood_type")
    val bloodType: String,
    @SerializedName("allergies")
    val allergies: String,
    @SerializedName("phone")
    val phone: String
)
