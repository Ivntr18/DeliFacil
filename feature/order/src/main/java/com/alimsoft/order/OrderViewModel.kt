package com.alimsoft.order

import android.util.Log
import com.alimsoft.common.core.CoroutineDispatchers
import androidx.lifecycle.viewModelScope
import com.alimsoft.common.ui.BaseViewModel
import com.alimsoft.domain.observer.ObserveTableList
import com.alimsoft.domain.repository.OrderRepository
import com.alimsoft.order.state.OrderUiEffect
import com.alimsoft.order.state.OrderUiEvent
import com.alimsoft.navigation.Navigator
import com.alimsoft.order.state.OrderUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OrderViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val orderRepository: OrderRepository,
    navigator: Navigator
) : BaseViewModel<OrderUiState, OrderUiEvent, OrderUiEffect>(OrderUiState()),
    Navigator by navigator {

    init {
        collectEvents()
        collectProductsToBeSend()
    }

    private fun collectEvents() = viewModelScope.launch(dispatchers.computation) {
        events.collect { event ->
            when (event) {
                OrderUiEvent.Refresh -> {

                }
                OrderUiEvent.SendOrderProducts -> {
                    orderRepository.sendOrderProducts()
                }
                is OrderUiEvent.RemoveProduct -> {
                    orderRepository.removeOrderProduct(event.product)
                }
            }
        }
    }

    private fun collectProductsToBeSend() = viewModelScope.launch(dispatchers.computation) {
        orderRepository.getProductsToBeSend().collectLatest{
            Log.v("ivan: ", "collect products to be send $it")
            setState {
                copy(
                    productsToBeSend = it
                )
            }
        }
    }

}
