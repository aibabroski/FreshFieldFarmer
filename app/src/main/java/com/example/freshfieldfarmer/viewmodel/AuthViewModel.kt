package com.example.freshfieldfarmer.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freshfieldfarmer.data.api.ApiClient
import com.example.freshfieldfarmer.data.models.LoginRequest
import com.example.freshfieldfarmer.data.models.RegisterRequest
import com.example.freshfieldfarmer.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    // Function to handle login
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

    // Function to handle registration using the repository
    fun register(name: String, email: String, password: String, farmName: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // Prepare the register request
                val registerRequest = RegisterRequest(name, email, password, farmName)
                Log.d("AuthViewModel", "Register request: $registerRequest")

                // Call the repository's register function
                val response = authRepository.register(registerRequest)
                Log.d("AuthViewModel", "Register response: $response")

                if (response.isSuccessful) {
                    onResult(true) // Registration successful
                } else {
                    Log.e("AuthViewModel", "Registration failed: ${response.message()}")
                    onResult(false) // Registration failed
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error during registration: ${e.message}")
                onResult(false) // Network or server error
            }
        }
    }

}
