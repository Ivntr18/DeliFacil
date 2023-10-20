package com.alimsoft.domain.repository

import android.util.Log
import com.alimsoft.domain.entities.TableData
import io.ktor.client.call.body
import org.json.JSONArray

class OrderRepositoryImpl(
    private val apiRouteProvider: ApiRouteProvider,
    private val networkRepository: NetworkRepository
) : OrderRepository {
    override suspend fun getTables(): Result<List<TableData>> {
        val result = networkRepository.get(apiRouteProvider.getTablesRoute())
        return if (result.isSuccess) {
            try {
                val tableList = mutableListOf<TableData>()
                val jsonArray = JSONArray(result.getOrNull()?.body<String>())
                for (index in 0 until jsonArray.length()) {
                    val jsonObj = jsonArray.getJSONObject(index)
                    val data = TableData(
                        id = jsonObj.getInt("id"),
                        name = jsonObj.getString("identificador")
                    )
                    tableList.add(data)
                }
                Result.success(tableList)
            } catch (e: Exception) {
                Result.failure(Throwable("Error OrderRepository.getTables (result success)"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Throwable("Error OrderRepository.getTables"))
        }
    }

}
