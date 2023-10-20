package com.alimsoft.navigation

import com.alimsoft.navigation.deeplink.DeepLinkHandler
import com.alimsoft.navigation.deeplink.DeepLinkHandlerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val navigationModule = module {

    singleOf(::NavigatorImpl) withOptions {
        bind<Navigator>()
    }

    single<DeepLinkHandler> { DeepLinkHandlerImpl(androidContext()) }

    viewModelOf(::NavigationViewModel)
}
