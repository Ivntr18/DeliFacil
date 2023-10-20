package com.alimsoft.login

import android.util.Log
import com.alimsoft.common.core.CoroutineDispatchers
import androidx.lifecycle.viewModelScope
import com.alimsoft.common.ui.BaseViewModel
import com.alimsoft.domain.repository.LoginRepository
import com.alimsoft.domain.repository.NetworkRepository
import com.alimsoft.home.graph.HomeDestination
import com.alimsoft.login.state.LoginUiEffect
import com.alimsoft.login.state.LoginUiEvent
import com.alimsoft.login.state.LoginUiState
import com.alimsoft.navigation.Navigator
import kotlinx.coroutines.launch

class LoginViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val loginRepository: LoginRepository,
    navigator: Navigator
) : BaseViewModel<LoginUiState, LoginUiEvent, LoginUiEffect>(LoginUiState()),
    Navigator by navigator {

    init {
        Log.e("ivan:", "init login view model")
        collectEvents()
        checkEnableButton()
    }

    private fun collectEvents() = viewModelScope.launch(dispatchers.computation) {
        events.collect { event ->
            when (event) {
                LoginUiEvent.Refresh -> {

                }
                LoginUiEvent.Login -> {
                    login(state.value.username, state.value.password)
                }
                is LoginUiEvent.UsernameChange -> {
                    usernameChange(event.text)
                }
                is LoginUiEvent.PasswordChange -> {
                    passwordChange(event.text)
                }
            }
        }
    }

    private fun usernameChange(text: String) {
        val newText = text.replace("\n", "").take(200)
        setState { copy(username = newText) }
    }

    private fun passwordChange(text: String) {
        val newText = text.replace("\n", "").take(200)
        setState { copy(password = newText) }
    }

    private fun checkEnableButton() {
        viewModelScope.launch(dispatchers.computation) {
            state.collect { loginState ->
                val usernameFieldValid = loginState.username.isNotEmpty()
                val passwordFieldValid = loginState.password.isNotEmpty()
                setState { copy(isButtonEnabled = usernameFieldValid && passwordFieldValid) }
            }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch(dispatchers.computation) {
            loginRepository.login(username, password).onSuccess {
                Log.e("ivan:", "login success")
                navigate(HomeDestination.route())
            }.onFailure {
                Log.e("ivan:", "login failure $it")
                setEffect { LoginUiEffect.ShowServiceError("Error al iniciar sesión ${it.message}") }
            }
        }
    }
}
