package com.alimsoft.common.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

val NavBackStackEntry.parentRoute: String?
    get() = destination.parent?.route

fun NavController.getParentEntry(currentEntry: NavBackStackEntry): NavBackStackEntry {
    val route = currentEntry.parentRoute
    requireNotNull(route) {
        "No parent found for entry $currentEntry"
    }
    return getBackStackEntry(route)
}

@Composable
fun NavController.rememberParentEntry(currentEntry: NavBackStackEntry) =
    remember(currentEntry) { getParentEntry(currentEntry).apply { addArgsToSavedState() } }

fun NavBackStackEntry.addArgsToSavedState() {
    val args = arguments
    args?.keySet()?.forEach { key ->
        savedStateHandle[key] = args[key]
    }
}

@Composable
inline fun <reified T : ViewModel> NavController.rememberParentViewModel(
    currentEntry: NavBackStackEntry,
    parentEntry: NavBackStackEntry = rememberParentEntry(currentEntry)
): T = getViewModel(viewModelStoreOwner = parentEntry) {
    parametersOf(parentEntry.savedStateHandle)
}
