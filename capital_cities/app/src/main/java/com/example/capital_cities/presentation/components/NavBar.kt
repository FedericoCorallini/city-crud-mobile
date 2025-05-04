package com.example.capital_cities.presentation.components

import androidx.compose.material.icons.outlined.Search
import com.example.capital_cities.presentation.navigation.Home
import com.example.capital_cities.presentation.navigation.Search
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavBar (navController: NavController){
    NavigationBar(
        modifier = Modifier.padding(1.dp).border(1.dp, color = Color.Black, shape = RectangleShape),
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = {
                navController.navigate(Home)
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Search, contentDescription = "Procedures") },
            label = { Text("Search") },
            selected = false,
            onClick = {
                navController.navigate(Search)
            }
        )
    }
}