package com.alimsoft.navigation.deeplink

interface Deeplinkable<T> {
    fun T.isNeeded(): Boolean
}

interface DeeplinkDestination<T, P> {
    fun build(params: P? = null): T
}
