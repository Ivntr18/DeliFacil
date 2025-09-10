package com.alimsoft.menu.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.common.ui.util.rememberParentViewModel
import com.alimsoft.menu.MenuViewModel

@Composable
fun rememberMenuScreenState(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    viewModel: MenuViewModel = navController.rememberParentViewModel(currentEntry = backStackEntry),
): MenuScreenState {
    return remember(viewModel) {
        MenuScreenState(viewModel)
    }
}

class MenuScreenState(
    private val viewModel: MenuViewModel
) {

    val uiState = viewModel.state

    val navigate: (String) -> Unit = { route ->
        viewModel.navigate(route)
    }

    fun dispatch(event: MenuUiEvent) = viewModel.dispatch(event)
}
