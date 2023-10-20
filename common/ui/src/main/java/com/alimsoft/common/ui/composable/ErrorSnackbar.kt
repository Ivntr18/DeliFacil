package com.alimsoft.common.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorSnackBar(snackbarData: SnackbarData) {
    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(6.dp),
        backgroundColor = MaterialTheme.colors.error
    ) {
        Text(
            text = snackbarData.message,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(6.dp).fillMaxWidth()
        )
    }
}

@Composable
fun ErrorSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(hostState = hostState, modifier = modifier) { ErrorSnackBar(it) }
}
