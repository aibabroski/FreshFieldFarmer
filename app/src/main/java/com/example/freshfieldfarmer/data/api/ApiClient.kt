package com.example.freshfieldfarmer.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://farmermarketsystem-production.up.railway.app/"

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
