package com.example.freshfieldfarmer.data.api

import com.example.freshfieldfarmer.data.models.Product
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {
    @GET("products")
    suspend fun getProducts(@Header("Authorization") token: String): Response<List<Product>>

    @POST("products")
    suspend fun addProduct(
        @Header("Authorization") token: String,
        @Body product: Product
    ): Response<Unit>

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body product: Product
    ): Response<Unit>

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Unit>
}
