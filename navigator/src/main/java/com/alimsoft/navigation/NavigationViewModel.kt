package com.alimsoft.navigation

import androidx.lifecycle.ViewModel

class NavigationViewModel(private val navigator: Navigator) : ViewModel(), Navigator by navigator
