package com.alimsoft.menu.state

import com.alimsoft.common.ui.UiEffect


sealed interface MenuUiEffect : UiEffect {
    data class ShowServiceError(val message: String) : MenuUiEffect
}
