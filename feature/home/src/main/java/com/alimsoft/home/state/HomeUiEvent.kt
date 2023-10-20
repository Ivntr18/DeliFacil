package com.alimsoft.home.state

import com.alimsoft.common.ui.UiEvent
import com.alimsoft.domain.entities.TableData

sealed interface HomeUiEvent : UiEvent {
    object Refresh : HomeUiEvent
    data class SelectTable(val table: TableData) : HomeUiEvent
}
