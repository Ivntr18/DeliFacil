package com.alimsoft.domain.repository

import android.util.Log
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.MenuProductData
import com.alimsoft.domain.entities.TableData
import io.ktor.client.call.body
import org.json.JSONArray

class MenuRepositoryImpl(
    private val apiRouteProvider: ApiRouteProvider,
    private val networkRepository: NetworkRepository
) : MenuRepository {
    override suspend fun getCategories(): Result<List<MenuCategoryData>> {
        val result = networkRepository.get(apiRouteProvider.getMenuCategoriesRoute())
        return if (result.isSuccess) {
            try {
                val dataList = mutableListOf<MenuCategoryData>()
                val jsonArray = JSONArray(result.getOrNull()?.body<String>())
                for (index in 0 until jsonArray.length()) {
                    val jsonObj = jsonArray.getJSONObject(index)
                    val data = MenuCategoryData(
                        id = jsonObj.getInt("id"),
                        name = jsonObj.getString("categoria")
                    )
                    dataList.add(data)
                }
                Result.success(dataList)
            } catch (e: Exception) {
                Result.failure(Throwable("Error MenuRepository.getCategories (result success)"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Throwable("Error MenuRepository.getCategories"))
        }
    }

    override suspend fun getProducts(category: MenuCategoryData): Result<List<MenuProductData>> {
        val result = networkRepository.get(apiRouteProvider.getMenuProductRoute(category))
        return if (result.isSuccess) {
            try {
                val dataList = mutableListOf<MenuProductData>()
                val jsonArray = JSONArray(result.getOrNull()?.body<String>())
                for (index in 0 until jsonArray.length()) {
                    val jsonObj = jsonArray.getJSONObject(index)
                    val data = MenuProductData(
                        id = jsonObj.getInt("id"),
                        name = jsonObj.getString("nombre")
                    )
                    dataList.add(data)
                }
                Result.success(dataList)
            } catch (e: Exception) {
                Result.failure(Throwable("Error MenuRepository.getProducts (result success)"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Throwable("Error MenuRepository.getProducts"))
        }
    }

}
