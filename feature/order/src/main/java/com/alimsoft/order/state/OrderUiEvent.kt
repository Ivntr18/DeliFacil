package com.alimsoft.order.state

import com.alimsoft.common.ui.UiEvent
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.OrderProductData
import com.alimsoft.domain.entities.TableData

sealed interface OrderUiEvent : UiEvent {
    object Refresh : OrderUiEvent
    object SendOrderProducts : OrderUiEvent
    data class RemoveProduct(val product: OrderProductData) : OrderUiEvent
}
