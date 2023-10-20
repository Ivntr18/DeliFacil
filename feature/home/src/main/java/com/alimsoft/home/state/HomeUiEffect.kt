package com.alimsoft.home.state

import com.alimsoft.common.ui.UiEffect


sealed interface HomeUiEffect : UiEffect {
    data class ShowServiceError(val message: String) : HomeUiEffect
}
