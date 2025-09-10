package com.alimsoft.bottomnavigation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alimsoft.bottomnavigation.state.BottomNavTab

@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    item: BottomNavTab
) {
    BottomNavigationItem(
        selected = selected,
        unselectedContentColor = Color(0xFFA2ACB7),
        //selectedContentColor = Secondary.Red,
        onClick = onClick,
        icon = {
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .width(40.dp)
                    .padding(top = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = item.iconRes),
                    contentDescription = stringResource(id = item.textRes)
                )
            }
        },
        label = {
            Text(
                text = stringResource(id = item.textRes),
                style = MaterialTheme.typography.body2,
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    )
}
