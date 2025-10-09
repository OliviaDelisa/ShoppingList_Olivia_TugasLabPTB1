package com.example.shoppinglist.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.components.ItemInput
import com.example.shoppinglist.components.SearchInput
import com.example.shoppinglist.components.ShoppingList

@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier
) {
    // State
    var newItemText by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    val shoppingItems = remember { mutableStateListOf<String>() }

    val filteredItems by remember(searchQuery, shoppingItems) {
        derivedStateOf {
            if (searchQuery.isBlank()) shoppingItems
            else shoppingItems.filter { it.contains(searchQuery, ignoreCase = true) }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(Modifier.height(12.dp))

        ItemInput(
            text = newItemText,
            onTextChange = { newItemText = it },
            onAddItem = {
                if (newItemText.isNotBlank()) {
                    shoppingItems.add(newItemText)
                    newItemText = ""
                }
            }
        )

        Spacer(Modifier.height(12.dp))

        SearchInput(
            query = searchQuery,
            onQueryChange = { searchQuery = it }
        )

        Spacer(Modifier.height(12.dp))

        ShoppingList(items = filteredItems)
    }
}
