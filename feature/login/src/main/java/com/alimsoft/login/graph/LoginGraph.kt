package com.alimsoft.login.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alimsoft.login.ui.composable.LoginScreen

fun NavGraphBuilder.loginGraph(
    navController: NavController,
    //dispatchActivityEffect: (MantenimientoActivityEffect) -> Unit
) =
    navigation(
        startDestination = LoginDestination.logincreenRoute(),
        route = LoginDestination.route()
    ) {
        composable(route = LoginDestination.logincreenRoute()) {
            LoginScreen(navController, it)
        }

    }
