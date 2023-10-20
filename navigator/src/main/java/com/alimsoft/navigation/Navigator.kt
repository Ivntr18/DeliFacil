package com.alimsoft.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow

interface Navigator {
    fun navigateUp(): Boolean
    fun popBackStack()
    fun popUpTo(route: String, inclusive: Boolean = false, saveState: Boolean = false)
    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    ): Boolean

    fun navigateClearTop(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    ): Boolean

    fun deeplinkTo(deeplink: String)

    val destinations: Flow<NavigationEvent>
}

fun topLevelNavigationOptions(
    startDestination: NavDestination,
    additionalOptions: NavOptionsBuilder.() -> Unit = {}
): NavOptionsBuilder.() -> Unit = {
    // Pop up to the start destination of the graph to
    // avoid building up a large stack of destinations
    // on the back stack as users select items
    popUpTo(startDestination.id) {
        saveState = true
    }
    // Avoid multiple copies of the same destination when
    // reselecting the same item
    launchSingleTop = true
    // Restore state when reselecting a previously selected item
    restoreState = true
    additionalOptions()
}
