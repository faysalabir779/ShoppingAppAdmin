package com.example.shoppingappadmin.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.shoppingappadmin.presentation.screens.AddProductsScreen.AddProductsScreen
import com.example.shoppingappadmin.presentation.screens.CategoryScreen.CategoryScreen
import com.example.shoppingappadmin.presentation.screens.DashboardScreen.DashboardScreen
import com.example.shoppingappadmin.presentation.screens.NotificationScreen.NotificationScreen
import com.example.shoppingappadmin.presentation.screens.OrdersScreen.OrdersScreen
import com.example.shoppingappadmin.presentation.view_model.ShoppingAppViewModel

@Composable
fun App(viewModel: ShoppingAppViewModel) {

    val bottomBarItem = listOf(
        BottomBarItem(
            name = "Dashboard",
            Icons.Filled.Dashboard
        ),
        BottomBarItem(
            name = "Add Products",
            Icons.Filled.Add
        ),
        BottomBarItem(
            name = "Notification",
            Icons.Filled.Notifications
        ),
        BottomBarItem(
            name = "Category",
            Icons.Filled.Category
        ),
        BottomBarItem(
            name = "Orders",
            Icons.Filled.ShoppingCart
        )
    )

    var selectedIndex by rememberSaveable { mutableStateOf(1) }

    val navController = rememberNavController()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold (bottomBar = {
            NavigationBar {
                bottomBarItem.forEachIndexed { index, bottomBarItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = { Icon(imageVector = bottomBarItem.icon, contentDescription = null) },
                        label = {
                            Text(text = bottomBarItem.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }){
            Box (modifier = Modifier.fillMaxSize().padding(it)){
                when(selectedIndex){
                    0 -> DashboardScreen()
                    1 -> AddProductsScreen(viewModel)
                    2 -> NotificationScreen()
                    3 -> CategoryScreen()
                    4 -> OrdersScreen()
                }
            }

        }
    }


}


data class BottomBarItem(
    val name: String,
    val icon: ImageVector,
)