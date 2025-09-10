package com.alimsoft.menu

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val menuModule = module {
    viewModelOf(::MenuViewModel)
}
