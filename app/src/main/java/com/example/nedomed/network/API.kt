package com.example.nedomed.network


import com.example.nedomed.network.model.AppointmentBody
import com.example.nedomed.network.model.AppointmentResponse
import com.example.nedomed.network.model.DoctorResponse
import com.example.nedomed.network.model.PatientResponse
import com.example.nedomed.network.model.SignInModel
import com.example.nedomed.network.model.SignUpModel
import com.example.nedomed.network.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {

    @POST("api/login")
    suspend fun signIn(
        @Body signInModel: SignInModel
    ):String

    @POST("api/signup")
    suspend fun signUp(
        @Body signUpModel: SignUpModel
    ): SignUpResponse

    @GET("api/patient/{id}")
    fun getPatient(@Path("id") id: Int): PatientResponse

    @GET("api/doctor/{id}")
    fun getDoctor(@Path("id") id: Int): DoctorResponse

    @POST("api/appointment/{doctor_id}/schedule")
    suspend fun getDoctorSchedule(
        @Path("doctor_id") doctorId: String,
        @Body appointmentBody: AppointmentBody
    ): AppointmentResponse

}