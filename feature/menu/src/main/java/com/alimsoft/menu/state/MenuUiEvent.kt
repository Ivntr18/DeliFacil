package com.alimsoft.menu.state

import com.alimsoft.common.ui.UiEvent
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.MenuProductData

sealed interface MenuUiEvent : UiEvent {
    object Refresh : MenuUiEvent
    object AddProduct : MenuUiEvent
    data class SelectCategory(val category: MenuCategoryData) : MenuUiEvent
    data class SelectProduct(val product: MenuProductData) : MenuUiEvent
    data class ProductCommentChange(val text: String) : MenuUiEvent
}
