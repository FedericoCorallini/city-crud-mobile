package com.example.capital_cities.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(title : String, navigationIcon: @Composable () -> Unit = {}, actions: @Composable RowScope.() -> Unit = {},) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = Modifier
            .padding(1.dp)
            .border(1.dp, color = Color.Black, shape = RectangleShape),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}