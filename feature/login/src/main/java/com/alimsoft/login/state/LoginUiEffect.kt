package com.alimsoft.login.state

import com.alimsoft.common.ui.UiEffect


sealed interface LoginUiEffect : UiEffect {
    data class ShowServiceError(val message: String) : LoginUiEffect
}
