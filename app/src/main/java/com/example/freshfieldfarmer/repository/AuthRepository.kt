package com.example.freshfieldfarmer.repository

import com.example.freshfieldfarmer.data.api.AuthApi
import com.example.freshfieldfarmer.data.models.RegisterRequest
import retrofit2.Response

class AuthRepository(private val authApi: AuthApi) {
    suspend fun register(registerRequest: RegisterRequest): Response<Unit> {
        return authApi.register(registerRequest)
    }
}
