package com.alimsoft.order.state

import com.alimsoft.common.ui.UiState
import com.alimsoft.domain.entities.OrderProductData

data class OrderUiState(
    val isLoading: Boolean = false,
    val productsToBeSend: List<OrderProductData> = emptyList(),
) : UiState
