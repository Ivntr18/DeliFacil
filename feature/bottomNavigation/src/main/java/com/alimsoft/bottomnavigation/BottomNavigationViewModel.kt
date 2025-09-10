package com.alimsoft.bottomnavigation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alimsoft.bottomnavigation.graph.bottomTabItems
import com.alimsoft.bottomnavigation.state.BottomNavEvent
import com.alimsoft.bottomnavigation.state.BottomNavState
import com.alimsoft.bottomnavigation.state.BottomNavTab
import com.alimsoft.common.core.CoroutineDispatchers
import com.alimsoft.common.ui.BaseViewModel
import com.alimsoft.common.ui.UiEffect
import com.alimsoft.navigation.Navigator
import com.alimsoft.navigation.config.BottomNavConfig
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class BottomNavigationViewModel(
    private val dispatchers: CoroutineDispatchers,
    config: BottomNavConfig,
    navigator: Navigator
) : BaseViewModel<BottomNavState, BottomNavEvent, UiEffect>(
    BottomNavState(bottomTabs = bottomTabItems, config = config)
),
    Navigator by navigator {

    init {
        collectEvents()
    }

    private fun collectEvents() {
        viewModelScope.launch(dispatchers.computation) {
            events.collectLatest { event ->
                when (event) {
                    is BottomNavEvent.TabClicked -> navigateToTab(event.navTab)
                }
            }
        }
    }
    private fun navigateToTab(navTab: BottomNavTab) {
        navigate(navTab.route) {}
    }
}
