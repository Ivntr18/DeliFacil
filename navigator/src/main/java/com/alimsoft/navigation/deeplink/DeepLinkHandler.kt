package com.alimsoft.navigation.deeplink

interface DeepLinkHandler {
    fun isDeeplink(deeplink: String): Boolean
    fun process(deeplink: String)
}
