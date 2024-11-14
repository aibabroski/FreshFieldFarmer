package com.example.freshfieldfarmer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freshfieldfarmer.data.api.ApiClient
import com.example.freshfieldfarmer.data.models.LoginRequest
import com.example.freshfieldfarmer.data.models.RegisterRequest
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.authApi.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    // Save the token (use SharedPreferences or EncryptedStorage)
                    onResult(true)
                } else {
                    onResult(false) // Login failed
                }
            } catch (e: Exception) {
                onResult(false) // Network or server error
            }
        }
    }

    fun register(name: String, email: String, password: String, farmName: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.authApi.register(RegisterRequest(name, email, password, farmName))
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false) // Registration failed
                }
            } catch (e: Exception) {
                onResult(false) // Network or server error
            }
        }
    }

}
