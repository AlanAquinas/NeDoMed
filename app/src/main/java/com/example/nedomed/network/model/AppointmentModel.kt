package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName
import java.security.cert.CertPathValidatorException.Reason

data class AppointmentBody(
    @SerializedName("appointment_date")
    val appointmentDate : String,
    @SerializedName("start_time")
    val startTime : String,
    val reason : String,
    val notes : String
)

data class AppointmentResponse(
    val id : Int,
    @SerializedName("patient_id")
    val patientId : Int,
    @SerializedName("doctor_id")
    val doctorId : Int,
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime : String,
    val reason: String,
    @SerializedName("is_accepted")
    val isAccepted : Boolean,
    val notes: String
)
