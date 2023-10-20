package com.alimsoft.navigation.config

data class BottomNavConfig(
    val hiddenRoutes: List<String> = emptyList(),
    val topLevelRoutes: List<String> = emptyList()
)
