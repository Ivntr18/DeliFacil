package com.alimsoft.common.core

import org.koin.dsl.module

val commonCoreModule = module {
    single { CoroutineDispatchers() }
}
