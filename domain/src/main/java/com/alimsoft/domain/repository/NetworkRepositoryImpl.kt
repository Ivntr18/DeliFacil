package com.alimsoft.domain.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import org.json.JSONObject

class NetworkRepositoryImpl(
    private val httpClient: HttpClient,
) : NetworkRepository {

    private var token: String? = null

    override suspend fun get(route: String): Result<HttpResponse> {
        val response = httpClient.get(route) {
            header("Authorization", "Token $token")
        }
        return if (response.status == HttpStatusCode.OK) {
            Result.success(response)
        } else {
            Result.failure(Throwable(response.body<String>().toString()))
        }
    }

    override suspend fun post(route: String, params: Map<String, String>): Result<HttpResponse> {
        val json = JSONObject(params)
        val response = httpClient.post(route) {
            header("Authorization", "Token $token")
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }
        return if (response.status == HttpStatusCode.OK) {
            Result.success(response)
        } else {
            Result.failure(Throwable(response.body<String>().toString()))
        }
    }

    override suspend fun getToken(
        route: String,
        params: Map<String, String>
    ): Result<HttpResponse> {
        val json = JSONObject(params)
        val response = httpClient.post(route) {
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }
        return if (response.status == HttpStatusCode.OK) {
            token = JSONObject(response.body<String>()).getString("token")
            Result.success(response)
        } else {
            Result.failure(Throwable(response.body<String>().toString()))
        }
    }



}
