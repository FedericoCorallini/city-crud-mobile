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
                    CapitalCard(capital)
                }
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