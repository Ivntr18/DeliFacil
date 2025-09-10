package com.alimsoft.bottomnavigation.state

import com.alimsoft.common.ui.UiState
import com.alimsoft.navigation.config.BottomNavConfig

data class BottomNavState(
    val selectedTab: BottomNavTab? = null,
    val bottomTabs: List<BottomNavTab> = emptyList(),
    val config: BottomNavConfig = BottomNavConfig()
) : UiState
