package com.example.capital_cities.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.presentation.components.CapitalCard
import com.example.capital_cities.presentation.components.NavBar
import com.example.capital_cities.presentation.components.TopBar
import com.example.capital_cities.presentation.navigation.Insert
import com.example.capital_cities.presentation.theme.CapitalCitiesTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    HomeContent(viewModel.state, navController)
}

@Composable
fun HomeContent(state: HomeState, navController: NavController) {
    Scaffold(
        topBar = { TopBar("World Capital Cities", navController) },
        bottomBar = { NavBar(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Insert) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add city")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.capitalList) { capital ->
                CapitalCard(capital = capital)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    CapitalCitiesTheme(dynamicColor = false) {
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
