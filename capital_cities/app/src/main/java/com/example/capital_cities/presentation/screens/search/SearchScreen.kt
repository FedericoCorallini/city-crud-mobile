package com.example.capital_cities.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.capital_cities.domain.model.Capital
import com.example.capital_cities.presentation.components.CapitalCard
import com.example.capital_cities.presentation.components.NavBar
import com.example.capital_cities.presentation.components.TopBar
import com.example.capital_cities.presentation.theme.Capital_citiesTheme

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), navController: NavController) {
    SearchContent(viewModel.state, viewModel::onEvent, navController)
}

@Composable
fun SearchContent(state: SearchState, event: (SearchEvent) -> Unit, navController : NavController) {
    Scaffold(
        topBar = { TopBar("Search") },
        bottomBar = { NavBar(navController = navController) }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                    OutlinedTextField(
                        value = state.name,
                        onValueChange = { event(SearchEvent.ChangeName(it))},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { event(SearchEvent.Search) }
                        ),
                        placeholder = { Text("Search capital city...") },
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        maxLines = 1,
                        shape = RoundedCornerShape(26.dp)
                    )
            }
            items(state.capitalList){ capital ->
                CapitalCard(capital)
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    Capital_citiesTheme {
        //SearchContent(navController = rememberNavController())
    }
}