package com.alimsoft.bottomnavigation.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alimsoft.menu.graph.MenuDestination
import com.alimsoft.order.graph.OrderDestination
import com.alimsoft.strings.R.string as strings

enum class BottomNavTab(@StringRes val textRes: Int, val route: String, @DrawableRes val iconRes: Int) {
    ORDER(
        strings.bottom_nav_order_label,
        OrderDestination.route(),
        com.alimsoft.drawables.R.drawable.order_icon
    ),
    MENU(
        strings.bottom_nav_menu_label,
        MenuDestination.route(),
        com.alimsoft.drawables.R.drawable.menu_icon
    )
}
