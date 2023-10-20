package com.alimsoft.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph

@Suppress("RestrictedApi")
fun NavGraph.matchRoute(route: String): NavDestination.DeepLinkMatch? {
    val request = NavDeepLinkRequest.Builder.fromUri(NavDestination.createRoute(route).toUri()).build()
    return matchDeepLink(request)
}

fun NavController.matchRoute(route: String): NavDestination.DeepLinkMatch? = graph.matchRoute(route)
