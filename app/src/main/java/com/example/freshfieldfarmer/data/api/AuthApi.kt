package com.example.freshfieldfarmer.data.api

import com.example.freshfieldfarmer.data.models.LoginRequest
import com.example.freshfieldfarmer.data.models.LoginResponse
import com.example.freshfieldfarmer.data.models.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/farmer/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/farmer/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<Unit>
}
