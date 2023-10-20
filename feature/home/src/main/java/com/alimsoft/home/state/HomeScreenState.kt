package com.alimsoft.home.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.common.ui.util.rememberParentViewModel
import com.alimsoft.home.HomeViewModel

@Composable
fun rememberHomeScreenState(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    viewModel: HomeViewModel = navController.rememberParentViewModel(currentEntry = backStackEntry),
): HomeScreenState {
    return remember(viewModel) {
        HomeScreenState(viewModel)
    }
}

class HomeScreenState(
    private val viewModel: HomeViewModel
) {

    val uiState = viewModel.state

    val navigate: (String) -> Unit = { route ->
        viewModel.navigate(route)
    }

    fun dispatch(event: HomeUiEvent) = viewModel.dispatch(event)
}
