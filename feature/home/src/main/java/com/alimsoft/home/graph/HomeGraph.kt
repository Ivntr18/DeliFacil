package com.alimsoft.home.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.alimsoft.home.ui.composable.HomeOrderDialog
import com.alimsoft.home.ui.composable.HomeScreen

fun NavGraphBuilder.homeGraph(
    navController: NavController,
) =
    navigation(
        startDestination = HomeDestination.homeScreenRoute(),
        route = HomeDestination.route()
    ) {
        composable(route = HomeDestination.homeScreenRoute()) {
            HomeScreen(navController, it)
        }
        dialog(
            route = HomeDestination.homeOrderDialogRoute(),
            dialogProperties = HomeDestination.dialogProperties
        ) {
            HomeOrderDialog(navController, it)
        }

    }
