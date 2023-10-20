package com.alimsoft.domain.repository

const val hostUrl = "http://192.168.0.175:8080"
const val tokenPath = "/API/token"
const val tablesPath = "/API/mesas"

class ApiRouteProviderImpl() : ApiRouteProvider {
    override fun getTokenRoute() =
        "$hostUrl$tokenPath"

    override fun getTablesRoute() =
        "$hostUrl$tablesPath"

}
