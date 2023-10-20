@file:OptIn(ExperimentalCoroutinesApi::class)

package com.alimsoft.domain.observer

import android.util.Log
import com.alimsoft.common.core.CoroutineDispatchers
import com.alimsoft.domain.SubjectInteractor
import com.alimsoft.domain.entities.TableData
import com.alimsoft.domain.repository.OrderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ObserveTableList(
    private val orderRepo: OrderRepository,
    private val dispatchers: CoroutineDispatchers
) : SubjectInteractor<Unit, List<TableData>>() {

    override fun createObservable(params: Unit): Flow<List<TableData>> = flow {
        orderRepo.getTables()
            .onSuccess {
                emit(it)
            }
            .onFailure {
                Log.e("ObserveTableList", "Error createObservable")
                emit(emptyList<TableData>())
            }
    }.flowOn(dispatchers.io)


}
