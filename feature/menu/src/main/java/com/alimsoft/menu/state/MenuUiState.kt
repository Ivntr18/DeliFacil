package com.alimsoft.menu.state

import com.alimsoft.common.ui.UiState
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.MenuProductData

data class MenuUiState(
    val isLoading: Boolean = false,
    val menuCategories: List<MenuCategoryData> = emptyList(),
    val menuProducts: List<MenuProductData> = emptyList(),
    val productComment: String = "",
    val selectedProduct: MenuProductData? = null,
) : UiState
