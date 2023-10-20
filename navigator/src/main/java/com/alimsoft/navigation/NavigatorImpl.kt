package com.alimsoft.navigation

import android.net.Uri
import androidx.core.net.toUri
import androidx.navigation.NavOptionsBuilder
import com.alimsoft.navigation.deeplink.DeepLinkHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber

const val CB_URI_SCHEME = "crackerbarrel"
const val EMAIL_SCHEME = "mailto"

private const val SCHEME_POS = 1
private const val PATH_POS = 2

internal class NavigatorImpl(
    private val deepLinkHandler: DeepLinkHandler
) : Navigator {

    private val uriRegex by lazy { """(\w+):/?/?([^\s]+)""".toRegex() }

    private val events = Channel<NavigationEvent>()

    override val destinations = events.receiveAsFlow()

    override fun navigateUp(): Boolean = events.trySend(NavigationEvent.NavigateUp).isSuccess

    override fun popBackStack() {
        events.trySend(NavigationEvent.PopBackStack)
    }

    override fun popUpTo(route: String, inclusive: Boolean, saveState: Boolean) {
        events.trySend(NavigationEvent.PopUpTo(route, inclusive, saveState))
    }

    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean {
        Timber.v("Navigating to route: `$route`")

        if (deepLinkHandler.isDeeplink(route)) {
            events.trySend(NavigationEvent.DeepLink(route.toUri()))
            return true
        }

        val remainingRoute = navigateIfUri(route) ?: return true
        return events.trySend(NavigationEvent.Directions(remainingRoute, builder)).isSuccess
    }

    override fun navigateClearTop(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean {
        Timber.v("Navigating to route (clear top): `$route`")
        val remainingRoute = navigateIfUri(route) ?: return true
        return events.trySend(NavigationEvent.ClearTop(remainingRoute, builder)).isSuccess
    }

    override fun deeplinkTo(deeplink: String) = deepLinkHandler.process(deeplink)

    /**
     * Navigates to the route as a Uri and returns null if the route is an external URI.
     * Returns the path of the Uri if it is a `crackerbarrel://` URI. Returns the route itself,
     * otherwise.
     */
    private fun navigateIfUri(route: String): String? {
        uriRegex.matchEntire(route)?.groupValues?.let { groupValues ->
            Timber.v("Route is a uri, scheme: ${groupValues[SCHEME_POS]}, path: ${groupValues[PATH_POS]}")
            if (groupValues[SCHEME_POS] == CB_URI_SCHEME) {
                return groupValues[PATH_POS]
            }
            if (Uri.parse(route).scheme == EMAIL_SCHEME) {
                events.trySend(NavigationEvent.SendEmailUri(Uri.parse(route)))
            } else {
                events.trySend(NavigationEvent.ViewUri(Uri.parse(route)))
            }
            return null
        }
        return route
    }
}
