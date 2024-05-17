package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class DoctorResponse(
    @SerializedName("user")
    val userId: Int,
    @SerializedName("specialization")
    val specialization: String,
    @SerializedName("qualifications")
    val qualifications: String,
    @SerializedName("experience_years")
    val experienceYears: Int,
    @SerializedName("license_number")
    val licenseNumber: String,
    @SerializedName("room")
    val room: String
)
