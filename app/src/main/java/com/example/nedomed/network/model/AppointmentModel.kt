package com.example.nedomed.network.model

import com.google.gson.annotations.SerializedName

data class AppointmentBody(
    @SerializedName("doctor_id")
    val doctorId: Int,
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("reason")
    val reason: String
)

data class AppointmentResponseGet(
    val id: Int,
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    val reason: String,
    @SerializedName("is_accepted")
    val isAccepted: Boolean,
    val notes: String?,
    @SerializedName("patient")
    val patientId: Int,
    @SerializedName("doctor")
    val doctorId: Int
)

data class AppointmentResponsePost(
    @SerializedName("id")
    val id: Int,
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("is_accepted")
    val isAccepted: Boolean,
    @SerializedName("notes")
    val notes: String?
)
