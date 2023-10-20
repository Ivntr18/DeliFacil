package com.alimsoft.login.state

import com.alimsoft.common.ui.UiEvent

sealed interface LoginUiEvent : UiEvent {
    object Refresh : LoginUiEvent
    object Login : LoginUiEvent
    data class UsernameChange(val text: String) : LoginUiEvent
    data class PasswordChange(val text: String) : LoginUiEvent
}
