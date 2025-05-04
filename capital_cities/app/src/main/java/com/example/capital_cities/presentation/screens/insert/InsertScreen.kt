package com.example.capital_cities.presentation.screens.insert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.capital_cities.presentation.components.NavBar
import com.example.capital_cities.presentation.components.TopBar
import com.example.capital_cities.presentation.theme.Capital_citiesTheme

@Composable
fun InsertScreen(viewModel: InsertViewModel = hiltViewModel(), navController: NavController) {
    InsertContent(viewModel.state, viewModel::onEventChange, navController)
}

@Composable
fun InsertContent(state: InsertState, onEvent: (InsertEvent) -> Unit, navController : NavController) {
    Scaffold(
        topBar = { TopBar("City") },
        bottomBar = { NavBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier.padding(top = 12.dp).padding(horizontal = 58.dp).fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = {onEvent.invoke(InsertEvent.ChangeName(it))},
                label = { Text(text = "Name") }
            )
            OutlinedTextField(
                value = state.country,
                onValueChange = {onEvent.invoke(InsertEvent.ChangeCountry(it))},
                label = { Text(text = "Country") }
            )
            OutlinedTextField(
                value = state.population.toString(),
                onValueChange = {onEvent.invoke(InsertEvent.ChangePopulation(it))},
                label = { Text(text = "Population") }
            )
            Button(
                onClick = {onEvent.invoke(InsertEvent.Save)},
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Save")
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    Capital_citiesTheme {
       // InsertContent(navController = rememberNavController())
    }
}