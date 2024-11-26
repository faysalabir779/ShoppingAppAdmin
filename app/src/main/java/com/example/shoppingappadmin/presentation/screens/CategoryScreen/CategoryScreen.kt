package com.example.shoppingappadmin.presentation.screens.CategoryScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingappadmin.domain.model.CategoryModel
import com.example.shoppingappadmin.presentation.view_model.ShoppingAppViewModel

@Preview(showBackground = true)
@Composable
fun CategoryScreen(viewModel: ShoppingAppViewModel = hiltViewModel()) {

    val categoryName = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = categoryName.value,
            onValueChange = { categoryName.value = it })
        Button(onClick = {
            viewModel.category.value = CategoryModel(name = categoryName.value)
            viewModel.addCategory()
            categoryName.value = ""
            Toast.makeText(context, "Category Added Successfully", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Submit")
        }
    }

}