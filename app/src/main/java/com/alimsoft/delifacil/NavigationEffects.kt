package com.alimsoft.delifacil

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import com.alimsoft.common.core.safeQueryIntentActivities
import com.alimsoft.navigation.NavigationEvent
import com.alimsoft.navigation.Navigator
import com.alimsoft.navigation.matchRoute
import com.alimsoft.navigation.topLevelNavigationOptions

@Composable
fun NavigationEffects(
    topLevelRoutes: List<String>,
    navController: NavController,
    navigator: Navigator
) {
    val context = LocalContext.current
    LaunchedEffect(navController) {
        navigator.destinations.collect { event ->
            when (event) {
                is NavigationEvent.Directions -> {
                    try {
                        val builder = event.checkTopLevelBuilder(navController, topLevelRoutes)
                        navController.navigate(event.destination, builder)
                    } catch (e: IllegalArgumentException) {
                        Log.e("Nav Effects", "Could not navigate to route: ${event.destination}")
                    }
                }

                NavigationEvent.NavigateUp -> {
                    navController.navigateUp()
                }

                NavigationEvent.PopBackStack -> {
                    navController.popBackStack()
                }

                is NavigationEvent.ViewUri -> {
                    val intent = Intent(Intent.ACTION_VIEW, event.uri)
                    if (context.safeQueryIntentActivities(intent, 0).isNotEmpty()) {
                        context.startActivity(intent)
                    } else {
                        Log.e("Nav Effects", "Unable to find activity for ${event.uri}")
                    }
                }

                is NavigationEvent.SendEmailUri -> {
                    val intent = Intent(Intent.ACTION_SENDTO, event.uri)
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Log.e("Nav Effects", "Unable to find activity for ${event.uri}")
                    }
                }

                is NavigationEvent.PopUpTo -> {
                    navController.popBackStack(event.route, event.inclusive, event.saveState)
                }

                is NavigationEvent.ClearTop -> {
                    val success = try {
                        navController.popBackStack(route = event.route, inclusive = false)
                    } catch (e: IllegalArgumentException) {
                        false
                    }
                    if (!success) navController.navigate(route = event.route, event.builder)
                }

                is NavigationEvent.DeepLink -> {
                    val uri = event.uri.buildUpon()
                        .path(event.uri.path?.trimEnd('/'))
                        .build()
                    try {
                        navController.navigate(uri)
                    } catch (e: Exception) {
                        Log.e("Nav Effects", "Failed to navigate to $uri")
                    }
                }
            }
        }
    }
}

private fun NavigationEvent.Directions.checkTopLevelBuilder(
    navController: NavController,
    topLevelRoutes: List<String>
): NavOptionsBuilder.() -> Unit {
    val matchDestination = navController.matchRoute(destination)
    return if (topLevelRoutes.contains(matchDestination?.destination?.route)) {
        topLevelNavigationOptions(navController.graph.findStartDestination()) {
            if (matchDestination?.matchingArgs?.isEmpty == false) {
                restoreState = false
            }
            builder()
        }
    } else {
        builder
    }
}
