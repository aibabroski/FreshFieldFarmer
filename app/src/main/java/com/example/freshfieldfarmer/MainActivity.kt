package com.example.freshfieldfarmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.freshfieldfarmer.data.api.ApiClient
import com.example.freshfieldfarmer.navigation.AppNavHost
import com.example.freshfieldfarmer.repository.AuthRepository
import com.example.freshfieldfarmer.viewmodel.AuthViewModel
import com.example.freshfieldfarmer.viewmodel.AuthViewModelFactory
import com.example.freshfieldfarmer.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {

    // Create the AuthRepository and pass it to the ViewModelFactory
    private val authRepository = AuthRepository(ApiClient.authApi)
    private val authViewModel: AuthViewModel by viewModels { AuthViewModelFactory(authRepository) }

    private val productViewModel = ProductViewModel() // Add ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavHost(navController, authViewModel, productViewModel) // Pass both ViewModels
        }
    }
}

