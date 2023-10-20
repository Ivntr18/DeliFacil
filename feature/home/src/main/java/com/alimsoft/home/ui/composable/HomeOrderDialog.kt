package com.alimsoft.home.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alimsoft.home.state.HomeScreenState
import com.alimsoft.home.state.HomeUiEvent
import com.alimsoft.home.state.rememberHomeScreenState
import com.alimsoft.drawables.R.drawable as drawables
import com.alimsoft.strings.R.string as strings

@Composable
fun HomeOrderDialog(
    navController: NavController,
    backstackEntry: NavBackStackEntry,
    screenState: HomeScreenState = rememberHomeScreenState(navController, backstackEntry)
) {
    val state by screenState.uiState.collectAsState()
    val navigate = screenState.navigate
    var chooseTableExpanded: Boolean by remember { mutableStateOf(false) }

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
                text = stringResource(id = strings.select_table),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(bottom = 16.dp).fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .clickable { chooseTableExpanded = true }.fillMaxWidth()
            ) {
                Text(
                    text = state.selectedTable?.name ?: "  ",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(end = 8.dp).weight(1f),
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(id = drawables.chevron_down),
                    contentDescription = null,
                    modifier = Modifier.height(30.dp)
                )
            }

            Box(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)){
                DropdownMenu(
                    expanded = chooseTableExpanded,
                    onDismissRequest = { chooseTableExpanded = false },
                ) {
                    state.tableList.forEach {
                        DropdownMenuItem(onClick = {
                            chooseTableExpanded = false
                            screenState.dispatch(HomeUiEvent.SelectTable(it))
                        }) {
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier
                                    .weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
