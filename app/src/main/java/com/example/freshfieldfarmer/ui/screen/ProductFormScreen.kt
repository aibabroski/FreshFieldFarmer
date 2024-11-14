package com.example.freshfieldfarmer.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freshfieldfarmer.data.models.Product
import com.example.freshfieldfarmer.viewmodel.ProductViewModel

@Composable
fun ProductFormScreen(navController: NavController, viewModel: ProductViewModel) {
    // State variables for product fields
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Get selected product from ViewModel
    val selectedProduct = viewModel.getSelectedProduct()

    // Pre-fill fields if editing an existing product
    LaunchedEffect(selectedProduct) {
        if (selectedProduct != null) {
            name = selectedProduct.name
            category = selectedProduct.category
            price = selectedProduct.price.toString()
            quantity = selectedProduct.quantity.toString()
            description = selectedProduct.description
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = if (selectedProduct == null) "Add Product" else "Edit Product", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Input Fields
        TextField(value = name, onValueChange = { name = it }, label = { Text("Product Name") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {
                val product = Product(
                    id = selectedProduct?.id,
                    name = name,
                    category = category,
                    price = price.toDoubleOrNull() ?: 0.0,
                    quantity = quantity.toIntOrNull() ?: 0,
                    description = description
                )
                if (selectedProduct == null) {
                    viewModel.addProduct(product) // Add new product
                } else {
                    viewModel.updateProduct(product) // Update existing product
                }
                navController.popBackStack() // Navigate back to the dashboard
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (selectedProduct == null) "Add Product" else "Save Changes")
        }
    }
}
