package com.alimsoft.domain.interactor

import com.alimsoft.domain.ResultInteractor
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.MenuProductData
import com.alimsoft.domain.repository.MenuRepository

class GetMenuProducts(
    private val menuRepository: MenuRepository
) : ResultInteractor<GetMenuProducts.Params, Result<List<MenuProductData>>>() {

    override suspend fun doWork(params: Params): Result<List<MenuProductData>> {
        return menuRepository.getProducts(params.category)
    }

    data class Params(val category: MenuCategoryData)
}
