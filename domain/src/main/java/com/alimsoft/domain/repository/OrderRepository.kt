package com.alimsoft.domain.repository

import com.alimsoft.domain.entities.MenuProductData
import com.alimsoft.domain.entities.OrderData
import com.alimsoft.domain.entities.OrderProductData
import com.alimsoft.domain.entities.TableData
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun getTables(): Result<List<TableData>>
    suspend fun startOrder(table: TableData): Result<OrderData>
    suspend fun addProduct(product: OrderProductData)
    suspend fun getProductsToBeSend(): Flow<List<OrderProductData>>
    suspend fun sendOrderProducts(): Result<String>
    suspend fun removeOrderProduct(product: OrderProductData)
}
