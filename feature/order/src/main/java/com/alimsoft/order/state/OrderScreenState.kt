package com.alimsoft.order.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.common.ui.util.rememberParentViewModel
import com.alimsoft.order.OrderViewModel

@Composable
fun rememberOrderScreenState(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    viewModel: OrderViewModel = navController.rememberParentViewModel(currentEntry = backStackEntry),
): OrderScreenState {
    return remember(viewModel) {
        OrderScreenState(viewModel)
    }
}

class OrderScreenState(
    private val viewModel: OrderViewModel
) {

    val uiState = viewModel.state

    val navigate: (String) -> Unit = { route ->
        viewModel.navigate(route)
    }

    fun dispatch(event: OrderUiEvent) = viewModel.dispatch(event)
}
