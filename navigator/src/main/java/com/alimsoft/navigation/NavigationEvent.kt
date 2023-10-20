package com.alimsoft.navigation

import android.net.Uri
import androidx.navigation.NavOptionsBuilder

sealed interface NavigationEvent {
    object NavigateUp : NavigationEvent
    object PopBackStack : NavigationEvent

    data class PopUpTo(
        val route: String,
        val inclusive: Boolean = false,
        val saveState: Boolean = false
    ) : NavigationEvent

    data class Directions(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit
    ) : NavigationEvent

    data class ClearTop(
        val route: String,
        val builder: NavOptionsBuilder.() -> Unit
    ) : NavigationEvent

    data class ViewUri(
        val uri: Uri
    ) : NavigationEvent

    data class SendEmailUri(
        val uri: Uri
    ) : NavigationEvent

    data class DeepLink(
        val uri: Uri
    ) : NavigationEvent
}
