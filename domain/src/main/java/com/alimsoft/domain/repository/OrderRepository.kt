package com.alimsoft.domain.repository

import com.alimsoft.domain.entities.TableData

interface OrderRepository {
    suspend fun getTables(): Result<List<TableData>>
}
