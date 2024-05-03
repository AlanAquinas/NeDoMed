package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class DoctorResponse(
    val id : Int,
    @SerializedName("first_name")
    val firstName : String,
    @SerializedName("last_name")
    val lastName : String,
    val specialization : String,
    val qualifications : String,
    @SerializedName("experience_years")
    val experienceYears : Int,
    @SerializedName("license_number")
    val licenseNumber : String,
    val room : String
)
