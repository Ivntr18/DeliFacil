package com.alimsoft.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class BaseViewModel<State : UiState, Event : UiEvent, Effect : UiEffect>(initialState: State) : ViewModel() {

    private val mutex = Mutex()

    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> get() = _events

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> get() = _state

    private val _effect: Channel<Effect> = Channel()
    val effects = _effect.receiveAsFlow()

    protected fun setState(reducer: State.() -> State) {
        viewModelScope.launch {
            mutex.withLock {
                val newState = _state.value.reducer()
                _state.emit(newState)
            }
        }
    }

    fun dispatch(event: Event) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }

    fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    fun <ReceiverEffect : UiEffect, DelegateEffect : UiEffect> BaseViewModel<*, *, ReceiverEffect>.delegateEffects(
        delegateViewModel: BaseViewModel<*, *, DelegateEffect>,
        dispatcher: CoroutineDispatcher,
        builder: (DelegateEffect) -> ReceiverEffect
    ) = viewModelScope.launch(dispatcher) {
        delegateViewModel.effects.collect { delegateEffect -> setEffect { builder(delegateEffect) } }
    }

    fun <ReceiverState : UiState, DelegateState : UiState> BaseViewModel<ReceiverState, *, *>.delegateState(
        delegateViewModel: BaseViewModel<DelegateState, *, *>,
        dispatcher: CoroutineDispatcher,
        reducer: ReceiverState.(DelegateState) -> ReceiverState
    ) = viewModelScope.launch(dispatcher) {
        delegateViewModel.state.collect { delegateState -> setState { reducer(delegateState) } }
    }

    fun <ReceiverState : UiState, DelegateState : UiState, ReceiverEffect : UiEffect, DelegateEffect : UiEffect> BaseViewModel<ReceiverState, *, ReceiverEffect>.delegate(
        delegateViewModel: BaseViewModel<DelegateState, *, DelegateEffect>,
        dispatcher: CoroutineDispatcher,
        effectBuilder: (DelegateEffect) -> ReceiverEffect,
        stateReducer: ReceiverState.(DelegateState) -> ReceiverState
    ) {
        delegateEffects(delegateViewModel, dispatcher, effectBuilder)
        delegateState(delegateViewModel, dispatcher, stateReducer)
    }
}
