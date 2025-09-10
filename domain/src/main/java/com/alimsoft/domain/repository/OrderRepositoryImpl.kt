package com.alimsoft.domain.repository

import android.util.Log
import com.alimsoft.domain.entities.OrderData
import com.alimsoft.domain.entities.OrderProductData
import com.alimsoft.domain.entities.TableData
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.json.JSONArray
import org.json.JSONObject

class OrderRepositoryImpl(
    private val apiRouteProvider: ApiRouteProvider,
    private val networkRepository: NetworkRepository
) : OrderRepository {

    private var currentOrder: OrderData? = null

    private val _productsToBeSend = MutableStateFlow<MutableList<OrderProductData>>(
        mutableListOf()
    )
    private val productsToBeSend: Flow<List<OrderProductData>> = _productsToBeSend
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

    override suspend fun startOrder(table: TableData): Result<OrderData> {
        val bodyParams = mapOf(
            "mesa" to table.id.toString(),
            "cantidadComensales" to "1"
        )
        val result = networkRepository.post(apiRouteProvider.getOrderRoute(), bodyParams)
        return if (result.isSuccess) {
            try {
                val jsonObj = JSONObject(result.getOrThrow().body<String>())
                val jsonUser = jsonObj.getJSONObject("usuario")
                val order = OrderData(
                    jsonObj.getInt("id"),
                    table,
                    jsonUser.getString("nombreCompleto")
                )
                currentOrder = order
                Result.success(order)
            } catch (e: Exception) {
                Result.failure(Throwable("Error OrderRepository.startOrder (result success)"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Throwable("Error OrderRepository.startOrder"))
        }
    }

    override suspend fun addProduct(product: OrderProductData) {
        //_productsToBeSend.value = _productsToBeSend.value.apply{ add(product) }
        _productsToBeSend.value.add(product)
    }

    override suspend fun getProductsToBeSend(): Flow<List<OrderProductData>> {
        return productsToBeSend
    }

    override suspend fun removeOrderProduct(product: OrderProductData) {
        //_productsToBeSend.emit(_productsToBeSend.value.apply { remove(product) })
        //_productsToBeSend.value.remove(product)
        val newList = _productsToBeSend.value.apply { remove(product) }
        val res = _productsToBeSend.tryEmit(newList)
        Log.v("ivan:", "remove order product try $res")
    }

    override suspend fun sendOrderProducts(): Result<String> {
        currentOrder?.let { order ->
            val productsSent = mutableListOf<OrderProductData>()
            _productsToBeSend.value.forEach {
                val result = sendProduct(order, it)
                if (result.isFailure) {
                    productsSent.forEach { sentProduct ->
                        _productsToBeSend.value.remove(sentProduct)
                    }
                    return Result.failure(result.exceptionOrNull() ?: Throwable("Error OrderRepository.sendOrderProducts ${order.id}"))
                }
                productsSent.add(it)
            }
            _productsToBeSend.value.clear()
            return Result.success("ok")
        }
        return Result.failure(Throwable("Error OrderRepository.sendOrderProducts null current order"))
    }

    private suspend fun sendProduct(order: OrderData, product: OrderProductData): Result<String> {
        val bodyParams = mutableMapOf(
            "articulo" to product.product.id.toString(),
            "orden" to order.id.toString()
        )
        if (product.comment.isNotBlank()) {
            bodyParams["comentario"] = product.comment
        }
        val result = networkRepository.post(apiRouteProvider.getSalesRoute(), bodyParams)
        return if (result.isSuccess) {
            Result.success("ok")
        } else {
            Result.failure(result.exceptionOrNull() ?: Throwable("Error OrderRepository.sendProduct ${product.product.name}"))
        }
    }

}
