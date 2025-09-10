package com.alimsoft.bottomnavigation.state

import com.alimsoft.common.ui.UiEvent

sealed class BottomNavEvent : UiEvent {
    data class TabClicked(val navTab: BottomNavTab) : BottomNavEvent()
}
