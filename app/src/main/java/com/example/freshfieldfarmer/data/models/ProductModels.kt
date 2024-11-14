package com.example.freshfieldfarmer.data.models

data class Product(
    val id: Int? = null,
    val name: String,
    val category: String,
    val price: Double,
    val quantity: Int,
    val description: String
)
