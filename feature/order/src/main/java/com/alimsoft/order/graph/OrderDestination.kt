package com.alimsoft.order.graph

import androidx.compose.ui.window.DialogProperties
import com.alimsoft.navigation.Destination

object OrderDestination : Destination {
    override fun route() = "orderGraph"

    fun orderScreenRoute() = "orderScreen"

    override val dialogProperties: DialogProperties
        get() = DialogProperties(usePlatformDefaultWidth = false)
}
