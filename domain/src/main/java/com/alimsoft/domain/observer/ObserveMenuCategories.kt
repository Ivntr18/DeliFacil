@file:OptIn(ExperimentalCoroutinesApi::class)

package com.alimsoft.domain.observer

import android.util.Log
import com.alimsoft.common.core.CoroutineDispatchers
import com.alimsoft.domain.SubjectInteractor
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.TableData
import com.alimsoft.domain.repository.MenuRepository
import com.alimsoft.domain.repository.OrderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ObserveMenuCategories(
    private val menuRepo: MenuRepository,
    private val dispatchers: CoroutineDispatchers
) : SubjectInteractor<Unit, List<MenuCategoryData>>() {

    override fun createObservable(params: Unit): Flow<List<MenuCategoryData>> = flow {
        menuRepo.getCategories()
            .onSuccess {
                emit(it)
            }
            .onFailure {
                Log.e("ObserveMenuCategories", "Error createObservable")
                emit(emptyList<MenuCategoryData>())
            }
    }.flowOn(dispatchers.io)


}
