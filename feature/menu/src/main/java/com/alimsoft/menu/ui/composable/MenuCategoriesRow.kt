package com.alimsoft.menu.ui.composable

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alimsoft.domain.entities.MenuCategoryData
import com.alimsoft.menu.state.MenuUiEvent

@Composable
fun MenuCategoriesRow(
    menuCategoryPair: List<MenuCategoryData>,
    dispatch: (MenuUiEvent) -> Unit,
) {
    val category2 = menuCategoryPair.getOrNull(1)
    Row(
        modifier = Modifier.fillMaxWidth().height(intrinsicSize = IntrinsicSize.Max)
    ) {
        Button(
            modifier = Modifier
                .weight(5f)
                .padding(10.dp)
                .fillMaxHeight(),
            onClick = { dispatch(MenuUiEvent.SelectCategory(menuCategoryPair[0]))  },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = menuCategoryPair[0].name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
        category2?.let {
            Button(
                modifier = Modifier
                    .weight(5f)
                    .padding(10.dp),
                onClick = { dispatch(MenuUiEvent.SelectCategory(it)) },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }
        } ?: kotlin.run {
            Spacer(modifier = Modifier.weight(5f))
        }
    }
}