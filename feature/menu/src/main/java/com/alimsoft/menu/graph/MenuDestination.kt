package com.alimsoft.menu.graph

import androidx.compose.ui.window.DialogProperties
import com.alimsoft.navigation.Destination

object MenuDestination : Destination {
    override fun route() = "menuGraph"

    fun menuScreenRoute() = "menuScreen"

    fun menuProductScreenRoute() = "menuProductScreen"

    fun menuProductAddDialogRoute() = "menuProductAddDialog"

    override val dialogProperties: DialogProperties
        get() = DialogProperties(usePlatformDefaultWidth = false)
}
