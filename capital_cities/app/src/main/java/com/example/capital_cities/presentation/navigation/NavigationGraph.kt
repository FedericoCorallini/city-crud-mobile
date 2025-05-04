package com.example.capital_cities.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capital_cities.presentation.screens.home.HomeScreen
import com.example.capital_cities.presentation.screens.insert.InsertScreen
import com.example.capital_cities.presentation.screens.search.SearchScreen

@Composable
fun NavigationGraph() {
   val navController = rememberNavController()
   NavHost(navController = navController, startDestination = Insert) {
      composable<Home> {
         HomeScreen(navController = navController)
      }
      composable<Search> {
         SearchScreen(navController = navController)
      }
      composable<Insert> {
         InsertScreen(navController = navController)
      }
   }
}