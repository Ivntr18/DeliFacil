package com.alimsoft.login.graph

import com.alimsoft.navigation.Destination

object LoginDestination : Destination {
    override fun route() = "loginGraph"

    fun logincreenRoute() = "loginScreen"
}
