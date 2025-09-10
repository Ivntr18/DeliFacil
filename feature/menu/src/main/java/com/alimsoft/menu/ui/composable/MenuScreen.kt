package com.alimsoft.menu.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.menu.state.MenuScreenState
import com.alimsoft.menu.state.rememberMenuScreenState

@Composable
fun MenuScreen(
    navController: NavController,
    backstackEntry: NavBackStackEntry,
    screenState: MenuScreenState = rememberMenuScreenState(navController, backstackEntry)
) {
    val state by screenState.uiState.collectAsState()
    val navigate = screenState.navigate


    Scaffold(

    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.SpaceBetween
                )
                {
                    items(state.menuCategories.chunked(2)) {
                        MenuCategoriesRow(
                            menuCategoryPair = it,
                            dispatch = screenState::dispatch
                        )
                    }
                }
            }
        }
    }
}
