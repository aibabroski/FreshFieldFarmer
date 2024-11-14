package com.example.freshfieldfarmer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.freshfieldfarmer.ui.DashboardScreen
import com.example.freshfieldfarmer.ui.screen.LoginScreen
import com.example.freshfieldfarmer.ui.screen.ProductFormScreen
import com.example.freshfieldfarmer.viewmodel.AuthViewModel
import com.example.freshfieldfarmer.viewmodel.ProductViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    productViewModel: ProductViewModel // Accept ProductViewModel
) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("dashboard") { DashboardScreen(navController, productViewModel) }
        composable("product_form") { ProductFormScreen(navController, productViewModel) }
    }
}
