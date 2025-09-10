package com.alimsoft.menu.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
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
import com.alimsoft.menu.state.MenuScreenState
import com.alimsoft.menu.state.MenuUiEvent
import com.alimsoft.menu.state.rememberMenuScreenState
import com.alimsoft.drawables.R.drawable as drawables
import com.alimsoft.strings.R.string as strings

@Composable
fun MenuProductAddDialog(
    navController: NavController,
    backstackEntry: NavBackStackEntry,
    screenState: MenuScreenState = rememberMenuScreenState(navController, backstackEntry)
) {
    val state by screenState.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .background(MaterialTheme.colors.background, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth().padding(30.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Text(
                text = state.selectedProduct?.name ?: "",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            OutlinedTextField(
                value = state.productComment,
                onValueChange = {
                    screenState.dispatch(MenuUiEvent.ProductCommentChange(it))
                },
                label = { Text(text = stringResource(id = strings.add_product_comment)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                minLines = 3,
            )


            Button(
                modifier = Modifier.fillMaxWidth().padding(30.dp),
                onClick = { screenState.dispatch(MenuUiEvent.AddProduct) },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(id = strings.add_product_button),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }
        }
    }
}
