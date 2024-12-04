package com.example.shoppingappadmin.presentation.screens.AddProductsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shoppingappadmin.common.ResultState
import com.example.shoppingappadmin.presentation.view_model.ShoppingAppViewModel

@Composable
fun AddProductsScreen(viewModel: ShoppingAppViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.fetchCategory()
    }
    var productName by remember { mutableStateOf("") }
    var dropDown by remember { mutableStateOf(false) }

    val category = viewModel.fetchCategoryState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Add Products", fontSize = 25.sp, fontWeight = FontWeight.Bold)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = productName, onValueChange = { productName = it })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = productName, onValueChange = { productName = it })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = productName, onValueChange = { productName = it })
            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier) {
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    modifier = Modifier,
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Drop Down Icon",
                            tint = Color(0xFF111111),
                            modifier = Modifier
                                .rotate(if (dropDown) 180f else 0f)
                                .clickable { dropDown = !dropDown }
                        )
                    },
                    placeholder = { Text(text = "Select Product") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color(0xFF111111)
                    ),

                    )

                DropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .background(color = Color.White),
                    expanded = dropDown,
                    onDismissRequest = {
                        dropDown = false
                    },
                ) {
                    category.value.success.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(text = it.name)
                            },
                            onClick = {
                                productName = it.name
                                dropDown = false
                            })
                    }


                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = productName, onValueChange = { productName = it })
            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = { /*TODO*/ }, modifier = Modifier.height(48.dp)) {
                Text(text = "Add Product")
            }
        }
    }
}