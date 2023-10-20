package com.alimsoft.login.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.common.ui.composable.ErrorSnackbarHost
import com.alimsoft.login.state.LoginScreenState
import com.alimsoft.login.state.LoginUiEffect
import com.alimsoft.login.state.rememberLoginScreenState

@Composable
fun LoginScreen(
    navController: NavController,
    backstackEntry: NavBackStackEntry,
    screenState: LoginScreenState = rememberLoginScreenState(navController, backstackEntry)
) {
    val state by screenState.uiState.collectAsState()
    val effects = screenState.effects
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effects) {
        effects.collect { effect ->
            when (effect) {
                is LoginUiEffect.ShowServiceError ->
                    snackbarHostState.showSnackbar(effect.message, duration = SnackbarDuration.Long)
            }
        }
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        snackbarHost = {
            ErrorSnackbarHost(hostState = it, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LoginScreenContent(state, screenState::dispatch)
        }
    }
}
