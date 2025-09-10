package com.alimsoft.domain.repository

import com.alimsoft.domain.entities.MenuCategoryData

const val hostUrl = "http://192.168.0.175:8080"
const val tokenPath = "/API/token"
const val tablesPath = "/API/mesas"
const val categoriesPath = "/API/menuCategorias"
const val menuPath = "/API/menu"
const val orderPath = "/API/ordenes"
const val salesPath = "/API/ventas"

class ApiRouteProviderImpl() : ApiRouteProvider {
    override fun getTokenRoute() =
        "$hostUrl$tokenPath"

    override fun getTablesRoute() =
        "$hostUrl$tablesPath"

    override fun getMenuCategoriesRoute() =
        "$hostUrl$categoriesPath"

    override fun getMenuProductRoute(category: MenuCategoryData) =
        "$hostUrl$menuPath/${category.id}"

    override fun getOrderRoute() =
        "$hostUrl$orderPath"

    override fun getSalesRoute() =
        "$hostUrl$salesPath"

}
