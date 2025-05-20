package com.example.capital_cities.presentation.screens.insert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.capital_cities.presentation.components.TopBar
import com.example.capital_cities.presentation.theme.CapitalCitiesTheme

@Composable
fun InsertScreen(viewModel: InsertViewModel = hiltViewModel(), navController: NavController) {
    InsertContent(viewModel.state, viewModel::onEventChange, navController)
}

@Composable
fun InsertContent(state: InsertState, onEvent: (InsertEvent) -> Unit, navController: NavController) {
    Scaffold(
        topBar = { TopBar("New City", navController, true) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = { onEvent(InsertEvent.ChangeName(it)) },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = state.country,
                onValueChange = { onEvent(InsertEvent.ChangeCountry(it)) },
                label = { Text("Country") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = state.population.toString(),
                onValueChange = { onEvent(InsertEvent.ChangePopulation(it)) },
                label = { Text("Population") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Button(
                onClick = { onEvent(InsertEvent.Save) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save")
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    CapitalCitiesTheme {
       // InsertContent(navController = rememberNavController())
    }
}