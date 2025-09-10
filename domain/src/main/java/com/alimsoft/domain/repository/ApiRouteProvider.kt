package com.alimsoft.domain.repository

import com.alimsoft.domain.entities.MenuCategoryData

interface ApiRouteProvider {
    fun getTokenRoute(): String
    fun getTablesRoute(): String
    fun getMenuCategoriesRoute(): String
    fun getMenuProductRoute(category: MenuCategoryData): String
    fun getOrderRoute(): String
    fun getSalesRoute(): String
}
