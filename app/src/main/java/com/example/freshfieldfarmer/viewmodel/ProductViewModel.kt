package com.example.freshfieldfarmer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freshfieldfarmer.data.api.ApiClient
import com.example.freshfieldfarmer.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun fetchProducts(token: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.productApi.getProducts(token)
                if (response.isSuccessful) {
                    _products.value = response.body() ?: emptyList()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            try {
                val response = ApiClient.productApi.addProduct("Bearer <TOKEN>", product)
                if (response.isSuccessful) {
                    fetchProducts("<TOKEN>")
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                val response = ApiClient.productApi.updateProduct("Bearer <TOKEN>", product.id!!, product)
                if (response.isSuccessful) {
                    fetchProducts("<TOKEN>")
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deleteProduct(productId: Int) {
        viewModelScope.launch {
            try {
                val response = ApiClient.productApi.deleteProduct("Bearer <TOKEN>", productId)
                if (response.isSuccessful) {
                    _products.value = _products.value.filter { it.id != productId }
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private var selectedProduct: Product? = null

    // Function to select a product (e.g., for editing)
    fun selectProduct(product: Product) {
        selectedProduct = product
    }

    // Function to get the currently selected product
    fun getSelectedProduct(): Product? {
        return selectedProduct
    }

}
