package com.alimsoft.delifacil

import android.app.Application
import com.alimsoft.common.core.commonCoreModule
import com.alimsoft.domain.di.domainModule
import com.alimsoft.home.homeModule
import com.alimsoft.login.loginModule
import com.alimsoft.navigation.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DeliFacilApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DeliFacilApp)
            modules(
                homeModule,
                commonCoreModule,
                navigationModule,
                loginModule,
                domainModule
            )
        }
    }
}
