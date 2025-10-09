package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.navigation.NavGraph
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry.value?.destination?.route

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text(
                                text = "Menu",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                            NavigationDrawerItem(
                                label = { Text("Settings") },
                                selected = currentRoute == "settings",
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        navController.navigate("settings") {
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                    Text(
                                        when (currentRoute) {
                                            "shopping_list" -> "Shopping List"
                                            "profile" -> "Profile"
                                            "settings" -> "Settings"
                                            else -> ""
                                        }
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                                    label = { Text("Home") },
                                    selected = currentRoute == "shopping_list",
                                    onClick = {
                                        if (currentRoute != "shopping_list") {
                                            navController.navigate("shopping_list") {
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            Icons.Filled.Person,
                                            contentDescription = "Profile"
                                        )
                                    },
                                    label = { Text("Profile") },
                                    selected = currentRoute == "profile",
                                    onClick = {
                                        if (currentRoute != "profile") {
                                            navController.navigate("profile") {
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    ) { paddingValues ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            NavGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
