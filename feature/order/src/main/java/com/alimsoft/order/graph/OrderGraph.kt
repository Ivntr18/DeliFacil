package com.alimsoft.order.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alimsoft.order.ui.composable.OrderScreen

fun NavGraphBuilder.orderGraph(
    navController: NavController,
) =
    navigation(
        startDestination = OrderDestination.orderScreenRoute(),
        route = OrderDestination.route()
    ) {
        composable(route = OrderDestination.orderScreenRoute()) {
            OrderScreen(navController, it)
        }
    }
