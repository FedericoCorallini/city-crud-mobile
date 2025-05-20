package com.example.capital_cities.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.capital_cities.presentation.components.CapitalCard
import com.example.capital_cities.presentation.components.NavBar
import com.example.capital_cities.presentation.components.TopBar
import com.example.capital_cities.presentation.theme.CapitalCitiesTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton


@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), navController: NavController) {
    SearchContent(viewModel.state, viewModel::onEvent, navController)
}

@Composable
fun SearchContent(state: SearchState, onEvent: (SearchEvent) -> Unit, navController: NavController) {
    Scaffold(
        topBar = { TopBar("Search", navController) },
        bottomBar = { NavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = { onEvent(SearchEvent.ChangeName(it)) },
                placeholder = { Text("Search capital city...") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onEvent(SearchEvent.Search) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(26.dp)),
                shape = RoundedCornerShape(26.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(state.capitalList) { capital ->
                    CapitalCard(
                        capital = capital,
                        onDeleteClick = { onEvent(SearchEvent.ShowDeleteDialog(it)) }
                    )
                }
            }
            state.capitalToDelete?.let { capital ->
                AlertDialog(
                    onDismissRequest = { onEvent(SearchEvent.HideDeleteDialog) },
                    title = { Text("Confirm Delete") },
                    text = { Text("Are you sure you want to delete ${capital.name}?") },
                    confirmButton = {
                        TextButton(onClick = { onEvent(SearchEvent.ConfirmDelete) }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { onEvent(SearchEvent.HideDeleteDialog) }) {
                            Text("Cancel")
                        }
                    }
                )
            }
            if (state.capitalList.isNotEmpty()) {
                val country = state.capitalList.first().country
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onEvent(SearchEvent.ShowDeleteCountryDialog) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Delete all capitals from $country")
                }
            }

            if (state.showDeleteCountryDialog) {
                AlertDialog(
                    onDismissRequest = { onEvent(SearchEvent.HideDeleteCountryDialog) },
                    title = { Text("Confirm deletion") },
                    text = {
                        Text("Are you sure you want to delete all capitals from the country ${state.capitalList.firstOrNull()?.country}?")
                    },
                    confirmButton = {
                        TextButton(onClick = { onEvent(SearchEvent.ConfirmDeleteCountry) }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { onEvent(SearchEvent.HideDeleteCountryDialog) }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    CapitalCitiesTheme {
        //SearchContent(navController = rememberNavController())
    }
}