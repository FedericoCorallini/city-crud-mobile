package com.example.capital_cities.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.capital_cities.presentation.components.CapitalCard
import com.example.capital_cities.presentation.components.NavBar
import com.example.capital_cities.presentation.components.TopBar
import com.example.capital_cities.presentation.navigation.Insert


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    HomeContent(viewModel.state, viewModel, navController)
}

@Composable
fun HomeContent(state: HomeState, viewModel: HomeViewModel, navController: NavController) {
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(state.capitalList) { capital ->
                    CapitalCard(
                        capital = capital,
                        onEditClick = { viewModel.showEditDialog(capital) }
                    )
                }
            }

            if (state.showEditDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.hideEditDialog() },
                    title = { Text("Edit Population") },
                    text = {
                        Column {
                            Text("Enter new population for ${state.capitalToEdit?.name}")
                            OutlinedTextField(
                                value = state.newPopulation,
                                onValueChange = { viewModel.state.newPopulation = it },
                                singleLine = true
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { viewModel.confirmEdit() }) {
                            Text("Save")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { viewModel.hideEditDialog() }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}