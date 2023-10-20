package com.alimsoft.navigation.deeplink

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class DeepLinkHandlerImpl(private val context: Context) : DeepLinkHandler {

    private val SCHEME = "crackerbarrel"

    override fun isDeeplink(deeplink: String): Boolean {
        val uri = deeplink.toUri()
        return uri.scheme == SCHEME
    }

    override fun process(deeplink: String) {
        val taskDetailIntent = Intent(
            Intent.ACTION_VIEW,
            deeplink.toUri()
        )

        TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(taskDetailIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }.send(context, 0, null)
    }
}
