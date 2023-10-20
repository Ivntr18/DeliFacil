package com.alimsoft.home.graph

import androidx.compose.ui.window.DialogProperties
import com.alimsoft.navigation.Destination

object HomeDestination : Destination {
    override fun route() = "homeGraph"

    fun homeScreenRoute() = "homeScreen"
    fun homeOrderDialogRoute() = "homeOrderDialog"

    override val dialogProperties: DialogProperties
        get() = DialogProperties(usePlatformDefaultWidth = false)
}
