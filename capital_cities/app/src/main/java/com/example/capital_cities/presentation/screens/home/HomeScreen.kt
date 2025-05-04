package com.example.capital_cities.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.presentation.components.CapitalCard
import com.example.capital_cities.presentation.components.NavBar
import com.example.capital_cities.presentation.components.TopBar
import com.example.capital_cities.presentation.navigation.Insert
import com.example.capital_cities.presentation.theme.Capital_citiesTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    HomeContent(viewModel.state, navController)
}

@Composable
fun HomeContent(state: HomeState, navController : NavController) {
    Scaffold(
        topBar = { TopBar("Home") },
        bottomBar = { NavBar(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(Insert)},
                shape = CircleShape
            ) { Icon(imageVector = Icons.Default.Add,  contentDescription = "") }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(state.capitalList) { item: Capital ->
                CapitalCard(item)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    Capital_citiesTheme(dynamicColor = false) {
        HomeContent(HomeState(capitalList = listOf(
                Capital(
                    name = "Buenos Aires",
                    country = "Argentina",
                    population = 1234
                )
            )
        ), rememberNavController())
    }
}
