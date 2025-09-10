package com.alimsoft.order.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alimsoft.domain.entities.OrderProductData
import com.alimsoft.drawables.R
import com.alimsoft.order.state.OrderUiEvent

@Composable
fun OrderRowToBeSend(
    product: OrderProductData,
    dispatch: (OrderUiEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(vertical = 20.dp).weight(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = product.product.name,
                style = MaterialTheme.typography.h5,
            )
            Text(
                text = product.comment,
                style = MaterialTheme.typography.body1.copy(color = Color.DarkGray),
                modifier = Modifier.padding(start = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Button(
            modifier = Modifier,
            onClick = { dispatch(OrderUiEvent.RemoveProduct(product)) },
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.remove_icon),
                contentDescription = null
            )
        }
    }
}