package com.alimsoft.home.state

import com.alimsoft.common.ui.UiState
import com.alimsoft.domain.entities.TableData

data class HomeUiState(
    val isLoading: Boolean = false,
    val greeting: String = "Home DeliFacil",
    val options: List<HomeOption> = emptyList(),
    val selectedTable: TableData? = null,
    val tableList: List<TableData> = emptyList(),
) : UiState

data class HomeOption (
    val nameId: Int,
    val route: String
)
