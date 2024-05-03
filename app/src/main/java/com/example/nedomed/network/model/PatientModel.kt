package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class PatientResponse(
    val id : Int,
    @SerializedName("first_name")
    val firstName : String,
    @SerializedName("last_name")
    val lastName : String,
    @SerializedName("date_of_birth")
    val dateOfBirth : String,
    @SerializedName("blood_type")
    val bloodType : String,
    val allergies : String,
    val phone : String
)
