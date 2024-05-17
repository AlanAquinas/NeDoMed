package com.example.nedomed.network.api


import com.example.nedomed.network.model.AnalysResponse
import com.example.nedomed.network.model.AppointmentBody
import com.example.nedomed.network.model.AppointmentResponseGet
import com.example.nedomed.network.model.AppointmentResponsePost
import com.example.nedomed.network.model.DoctorResponse
import com.example.nedomed.network.model.PatientResponse
import com.example.nedomed.network.model.UserRequest
import com.example.nedomed.network.model.SignUpModel
import com.example.nedomed.network.model.SignUpResponse
import com.example.nedomed.network.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {

    @POST("api/token")
    suspend fun signIn(
        @Body signInModel: UserRequest
    ): Response<TokenResponse>

    @POST("api/signup")
    suspend fun signUp(
        @Body signUpModel: SignUpModel
    ): Response<SignUpResponse>

    @GET("api/patient/{id}")
    fun getPatient(@Path("id") id: Int): Response<PatientResponse>

    @GET("api/doctor/{id}")
    fun getDoctor(@Path("id") id: Int): Response<DoctorResponse>

    @POST("api/appointments/create")
    suspend fun getDoctorSchedule(
        @Body appointmentBody: AppointmentBody
    ): Response<AppointmentResponsePost>

    @GET("api/patient/{id}/appointments/")
    suspend fun getAppointmentsByUserId(
        @Path("id") id: Int
    ): Response<List<AppointmentResponseGet>>

    @GET("api/patient/{id}/analysis-results/")
    suspend fun getAnalysisResultsByUserId(
        @Path("id") id: Int
    ): Response<List<AnalysResponse>>

}