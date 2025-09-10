package com.alimsoft.domain.di

import com.alimsoft.domain.interactor.GetMenuProducts
import com.alimsoft.domain.observer.ObserveMenuCategories
import com.alimsoft.domain.observer.ObserveTableList
import com.alimsoft.domain.repository.ApiRouteProvider
import com.alimsoft.domain.repository.ApiRouteProviderImpl
import com.alimsoft.domain.repository.LoginRepository
import com.alimsoft.domain.repository.LoginRepositoryImpl
import com.alimsoft.domain.repository.MenuRepository
import com.alimsoft.domain.repository.MenuRepositoryImpl
import com.alimsoft.domain.repository.NetworkRepository
import com.alimsoft.domain.repository.NetworkRepositoryImpl
import com.alimsoft.domain.repository.OrderRepository
import com.alimsoft.domain.repository.OrderRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    single {
        HttpClient {
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 30_000
                socketTimeoutMillis = 30_000
            }
        }
    }
    single<ApiRouteProvider> { ApiRouteProviderImpl() }
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<OrderRepository> { OrderRepositoryImpl(get(), get()) }
    single<MenuRepository> { MenuRepositoryImpl(get(), get()) }
    factoryOf(::ObserveTableList)
    factoryOf(::ObserveMenuCategories)
    factoryOf(::GetMenuProducts)
}
