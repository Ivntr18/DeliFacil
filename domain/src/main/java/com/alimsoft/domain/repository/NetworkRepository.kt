package com.alimsoft.domain.repository

import io.ktor.client.statement.HttpResponse


interface NetworkRepository {
    suspend fun get(route: String): Result<HttpResponse>
    suspend fun post(route: String, params: Map<String, String>): Result<HttpResponse>
    suspend fun getToken(route: String, params: Map<String, String>): Result<HttpResponse>
}
