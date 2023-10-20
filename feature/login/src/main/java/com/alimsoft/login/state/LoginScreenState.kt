package com.alimsoft.login.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.common.ui.util.rememberParentViewModel
import com.alimsoft.login.LoginViewModel

@Composable
fun rememberLoginScreenState(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    viewModel: LoginViewModel = navController.rememberParentViewModel(currentEntry = backStackEntry),
): LoginScreenState {
    return remember(viewModel) {
        LoginScreenState(viewModel)
    }
}

class LoginScreenState(
    private val viewModel: LoginViewModel
) {

    val uiState = viewModel.state

    val navigate: (String) -> Unit = { route ->
        viewModel.navigate(route)
    }

    fun dispatch(event: LoginUiEvent) = viewModel.dispatch(event)

    val effects = viewModel.effects
}
