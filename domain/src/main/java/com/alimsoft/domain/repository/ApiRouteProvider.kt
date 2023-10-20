package com.alimsoft.domain.repository

interface ApiRouteProvider {
    fun getTokenRoute(): String
    fun getTablesRoute(): String
}
