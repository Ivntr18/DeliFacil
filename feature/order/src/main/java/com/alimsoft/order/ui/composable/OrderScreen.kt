package com.alimsoft.order.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.order.graph.OrderDestination
import com.alimsoft.order.state.OrderScreenState
import com.alimsoft.order.state.OrderUiEvent
import com.alimsoft.order.state.rememberOrderScreenState
import com.alimsoft.strings.R

@Composable
fun OrderScreen(
    navController: NavController,
    backstackEntry: NavBackStackEntry,
    screenState: OrderScreenState = rememberOrderScreenState(navController, backstackEntry)
) {
    val state by screenState.uiState.collectAsState()
    val navigate = screenState.navigate

    Scaffold(

    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Mesa #3",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    Text(
                        text = "Orden #348",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
                Divider(color = Color.Black, thickness = 2.dp)
                Text(
                    text = "Artículos",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                LazyColumn {
                    items(state.productsToBeSend) {
                        OrderRowToBeSend(
                            product = it,
                            dispatch = screenState::dispatch
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = { screenState.dispatch(OrderUiEvent.SendOrderProducts) },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Enviar órden",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
            }
        }
    }
}
