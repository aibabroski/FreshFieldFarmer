package com.example.freshfieldfarmer.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freshfieldfarmer.data.models.Product
import com.example.freshfieldfarmer.viewmodel.ProductViewModel

@Composable
fun DashboardScreen(navController: NavController, viewModel: ProductViewModel) {
    val products by viewModel.products.collectAsState(initial = emptyList()) // Collect StateFlow

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Products",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Add Product Button
        Button(
            onClick = { navController.navigate("product_form") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Product")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product List
        LazyColumn {
            items(products) { product ->
                ProductCard(product = product, onEdit = {
                    viewModel.selectProduct(product)
                    navController.navigate("product_form")
                }, onDelete = { product.id?.let { viewModel.deleteProduct(it) } })
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${product.name}", style = MaterialTheme.typography.bodyLarge)
            Text("Category: ${product.category}")
            Text("Price: $${product.price}")
            Text("Quantity: ${product.quantity}")
            Text(
                text = if (product.quantity < 5) "Low Stock!" else "In Stock",
                color = if (product.quantity < 5) Color.Red else Color.Green
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = onEdit) {
                    Text("Edit")
                }
                TextButton(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}
