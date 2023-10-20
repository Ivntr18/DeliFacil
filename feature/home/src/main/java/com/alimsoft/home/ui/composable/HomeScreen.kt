package com.alimsoft.home.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.home.state.HomeScreenState
import com.alimsoft.home.state.rememberHomeScreenState

@Composable
fun HomeScreen(
    navController: NavController,
    backstackEntry: NavBackStackEntry,
    screenState: HomeScreenState = rememberHomeScreenState(navController, backstackEntry)
) {
    val state by screenState.uiState.collectAsState()
    val navigate = screenState.navigate

    Scaffold(

    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.SpaceBetween
                )
                {
                    state.options.forEach { option ->
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(30.dp),
                            onClick = { navigate(option.route) },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = option.nameId),
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.padding(vertical = 20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
