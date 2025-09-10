package com.alimsoft.bottomnavigation.ui

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alimsoft.bottomnavigation.BottomNavigationViewModel
import com.alimsoft.bottomnavigation.state.BottomNavEvent
import org.koin.androidx.compose.getViewModel

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    viewModel: BottomNavigationViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showNav = remember(currentDestination, state.config.showedRoutes) {
        currentDestination.hasAnyRoute(state.config.showedRoutes)
    }
    if (showNav) {
        BottomNavigation(
            backgroundColor = Color.White,
            contentColor = Color.Black,
            elevation = 0.dp
        ) {
            state.bottomTabs.forEach { tabInfo ->
                BottomNavigationItem(
                    selected = currentDestination.hasAnyRoute(tabInfo.route),
                    onClick = { viewModel.dispatch(BottomNavEvent.TabClicked(tabInfo)) },
                    item = tabInfo
                )
            }
        }
    }
}

private fun NavDestination?.hasAnyRoute(route: String): Boolean =
    this?.hierarchy?.any { it.route == route } == true

private fun NavDestination?.hasAnyRoute(routes: Collection<String>): Boolean =
    this?.hierarchy?.any { dest -> routes.any { current -> current == dest.route } } == true
