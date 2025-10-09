package com.example.shoppinglist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppinglist.ui.theme.screens.ProfileScreen
import com.example.shoppinglist.ui.theme.screens.SettingScreen
import com.example.shoppinglist.ui.theme.screens.ShoppingListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "shopping_list"
    ) {
        composable("shopping_list") {
            ShoppingListScreen(
            )
        }

        composable("settings") {
            SettingScreen(
            )
        }

        composable("profile") {
            ProfileScreen(
            )
        }
    }
}
