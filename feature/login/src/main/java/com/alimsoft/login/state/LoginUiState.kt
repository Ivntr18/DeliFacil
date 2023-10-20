package com.alimsoft.login.state

import com.alimsoft.common.ui.UiState

data class LoginUiState(
    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val username: String = "",
    val password: String = "",
) : UiState
