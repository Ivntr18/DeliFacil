package com.alimsoft.domain.repository

import io.ktor.client.statement.HttpResponse


interface LoginRepository {
    suspend fun login(user: String, password: String): Result<String>
}
