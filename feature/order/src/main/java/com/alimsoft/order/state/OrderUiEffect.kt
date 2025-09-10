package com.alimsoft.order.state

import com.alimsoft.common.ui.UiEffect


sealed interface OrderUiEffect : UiEffect {
    data class ShowServiceError(val message: String) : OrderUiEffect
}
