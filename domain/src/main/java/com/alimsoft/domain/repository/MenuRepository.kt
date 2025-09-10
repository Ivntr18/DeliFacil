package com.alimsoft.domain.repository

import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.MenuProductData

interface MenuRepository {
    suspend fun getCategories(): Result<List<MenuCategoryData>>
    suspend fun getProducts(category: MenuCategoryData): Result<List<MenuProductData>>
}
