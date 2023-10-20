package com.alimsoft.domain.repository

class LoginRepositoryImpl(
    private val apiRouteProvider: ApiRouteProvider,
    private val networkRepository: NetworkRepository
) : LoginRepository {
    override suspend fun login(user: String, password: String): Result<String> {
        val bodyParams = mapOf(
            "username" to user,
            "password" to password
        )
        val result = networkRepository.getToken(apiRouteProvider.getTokenRoute(), bodyParams)
        return if (result.isSuccess) {
            Result.success("OK")
        } else {
            Result.failure(result.exceptionOrNull() ?: Throwable("Error LoginRepository.login "))
        }
    }

}
