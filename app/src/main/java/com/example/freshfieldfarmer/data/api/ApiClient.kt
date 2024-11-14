package com.example.freshfieldfarmer.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Replace this with your actual backend URL
    private const val BASE_URL = "http://10.0.2.2:8080/" // Localhost for emulator

    // Create Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Initialize ProductApi
    val productApi: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }

    // Initialize AuthApi
    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }
}
