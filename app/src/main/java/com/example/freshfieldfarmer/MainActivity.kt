package com.example.freshfieldfarmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.freshfieldfarmer.navigation.AppNavHost
import com.example.freshfieldfarmer.viewmodel.AuthViewModel
import com.example.freshfieldfarmer.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    private val authViewModel = AuthViewModel()
    private val productViewModel = ProductViewModel() // Add ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavHost(navController, authViewModel, productViewModel) // Pass both ViewModels
        }
    }
}
