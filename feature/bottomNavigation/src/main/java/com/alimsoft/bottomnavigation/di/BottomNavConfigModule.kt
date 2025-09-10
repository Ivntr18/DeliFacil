package com.alimsoft.bottomnavigation.di

import com.alimsoft.menu.graph.MenuDestination
import com.alimsoft.navigation.config.BottomNavConfig
import com.alimsoft.order.graph.OrderDestination
import org.koin.dsl.module

val bottomNavConfigModule = module {
    single {
        BottomNavConfig(
            showedRoutes = listOf(
                OrderDestination.route(),
                MenuDestination.route()
            )
        )
    }
}
