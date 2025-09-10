package com.alimsoft.menu.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.alimsoft.menu.ui.composable.MenuProductAddDialog
import com.alimsoft.menu.ui.composable.MenuProductScreen
import com.alimsoft.menu.ui.composable.MenuScreen

fun NavGraphBuilder.menuGraph(
    navController: NavController,
) =
    navigation(
        startDestination = MenuDestination.menuScreenRoute(),
        route = MenuDestination.route()
    ) {
        composable(route = MenuDestination.menuScreenRoute()) {
            MenuScreen(navController, it)
        }
        composable(route = MenuDestination.menuProductScreenRoute()) {
            MenuProductScreen(navController, it)
        }
        dialog(
            route = MenuDestination.menuProductAddDialogRoute(),
            dialogProperties = MenuDestination.dialogProperties
        ) {
            MenuProductAddDialog(navController, it)
        }
    }
