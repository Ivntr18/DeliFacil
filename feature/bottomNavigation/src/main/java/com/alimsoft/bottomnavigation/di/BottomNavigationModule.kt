package com.alimsoft.bottomnavigation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.alimsoft.bottomnavigation.BottomNavigationViewModel

val bottomNavigationModule = module {
    viewModelOf(::BottomNavigationViewModel)
}
