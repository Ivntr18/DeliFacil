package com.alimsoft.menu

import com.alimsoft.common.core.CoroutineDispatchers
import androidx.lifecycle.viewModelScope
import com.alimsoft.common.ui.BaseViewModel
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.domain.entities.OrderProductData
import com.alimsoft.domain.interactor.GetMenuProducts
import com.alimsoft.domain.observer.ObserveMenuCategories
import com.alimsoft.domain.repository.OrderRepository
import com.alimsoft.menu.graph.MenuDestination
import com.alimsoft.menu.state.MenuUiEffect
import com.alimsoft.menu.state.MenuUiEvent
import com.alimsoft.navigation.Navigator
import com.alimsoft.menu.state.MenuUiState
import kotlinx.coroutines.launch

class MenuViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val observeMenuCategories: ObserveMenuCategories,
    private val getMenuProducts: GetMenuProducts,
    private val orderRepository: OrderRepository,
    navigator: Navigator
) : BaseViewModel<MenuUiState, MenuUiEvent, MenuUiEffect>(MenuUiState()),
    Navigator by navigator {

    init {
        collectEvents()
        collectMenuCategories()
    }

    private fun collectEvents() = viewModelScope.launch(dispatchers.computation) {
        events.collect { event ->
            when (event) {
                MenuUiEvent.Refresh -> {

                }
                MenuUiEvent.AddProduct -> {
                    addProduct()
                }
                is MenuUiEvent.SelectCategory -> {
                    fetchMenuProducts(event.category)
                    navigate(MenuDestination.menuProductScreenRoute())
                }
                is MenuUiEvent.SelectProduct -> {
                    setState {
                        copy(
                            selectedProduct = event.product
                        )
                    }
                    navigate(MenuDestination.menuProductAddDialogRoute())
                }
                is MenuUiEvent.ProductCommentChange -> {
                    productCommentChange(event.text)
                }
            }
        }
    }

    private fun collectMenuCategories() = viewModelScope.launch(dispatchers.computation) {
        observeMenuCategories(Unit).flow.collect {
            setState {
                copy(
                    menuCategories = it
                )
            }
        }
    }

    private fun fetchMenuProducts(category: MenuCategoryData) = viewModelScope.launch(dispatchers.computation) {
        getMenuProducts.executeSync(GetMenuProducts.Params(category))
            .onSuccess {
                setState {
                    copy(
                        menuProducts = it
                    )
                }
            }
            .onFailure {  }
    }

    private fun productCommentChange(text: String) {
        val newText = text.replace("\n", "").take(350)
        setState { copy(productComment = newText) }
    }

    private suspend fun addProduct() {
        state.value.selectedProduct?.let {
            orderRepository.addProduct(
                OrderProductData(it, state.value.productComment)
            )
        }
    }

}
