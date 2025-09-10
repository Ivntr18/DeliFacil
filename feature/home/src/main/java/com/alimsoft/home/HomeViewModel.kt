package com.alimsoft.home

import android.util.Log
import com.alimsoft.common.core.CoroutineDispatchers
import androidx.lifecycle.viewModelScope
import com.alimsoft.common.ui.BaseViewModel
import com.alimsoft.domain.observer.ObserveTableList
import com.alimsoft.domain.repository.OrderRepository
import com.alimsoft.home.graph.HomeDestination
import com.alimsoft.home.state.HomeOption
import com.alimsoft.home.state.HomeUiEffect
import com.alimsoft.home.state.HomeUiEvent
import com.alimsoft.home.state.HomeUiState
import com.alimsoft.navigation.Navigator
import com.alimsoft.order.graph.OrderDestination
import kotlinx.coroutines.launch
import com.alimsoft.strings.R.string as strings

class HomeViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val observeTableList: ObserveTableList,
    private val orderRepository: OrderRepository,
    navigator: Navigator
) : BaseViewModel<HomeUiState, HomeUiEvent, HomeUiEffect>(HomeUiState()),
    Navigator by navigator {

    init {
        Log.e("ivan:", "init home view model")
        collectEvents()
        collectHomeOptions()
        collectTableList()
    }

    private fun collectEvents() = viewModelScope.launch(dispatchers.computation) {
        events.collect { event ->
            when (event) {
                HomeUiEvent.Refresh -> {

                }
                HomeUiEvent.StartOrder -> {
                    state.value.selectedTable?.let {
                        orderRepository.startOrder(it)
                        navigate(OrderDestination.route())
                    }
                }
                is HomeUiEvent.SelectTable -> {
                    setState {
                        copy(
                            selectedTable = event.table
                        )
                    }
                }
            }
        }
    }

    private fun collectHomeOptions() = viewModelScope.launch(dispatchers.computation) {
        setState {
            copy(
                options = listOf(
                    HomeOption(strings.home_option_take_order, HomeDestination.homeOrderDialogRoute()),
                    HomeOption(strings.home_option_close_order, "")
                )
            )
        }
    }

    private fun collectTableList() = viewModelScope.launch(dispatchers.computation) {
        observeTableList(Unit).flow.collect {
            setState {
                copy(
                    tableList = it
                )
            }
        }
    }

}
