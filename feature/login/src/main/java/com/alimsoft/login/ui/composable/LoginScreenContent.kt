package com.alimsoft.login.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.alimsoft.login.state.LoginUiEvent
import com.alimsoft.login.state.LoginUiState
import com.alimsoft.strings.R.string as strings

@Composable
fun LoginScreenContent(
    state: LoginUiState,
    dispatch: (LoginUiEvent) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = strings.login_title),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = state.username,
            onValueChange = {
                dispatch(LoginUiEvent.UsernameChange(it))
            },
            label = { Text(text = stringResource(id = strings.username)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp)
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                dispatch(LoginUiEvent.PasswordChange(it))
            },
            label = { Text(text = stringResource(id = strings.password)) },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    dispatch(LoginUiEvent.Login)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                enabled = state.isButtonEnabled
            ) {
                Text(text = stringResource(id = strings.login_button))
            }
        }

    }
}
