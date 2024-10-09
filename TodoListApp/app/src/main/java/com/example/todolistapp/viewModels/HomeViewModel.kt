package com.example.todolistapp.viewModels

import androidx.lifecycle.ViewModel
import com.example.todolistapp.uiStates.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _homeUIState = MutableStateFlow(HomeUIState())

    val homeUIState: StateFlow<HomeUIState>
        get() {
            return _homeUIState.asStateFlow()
        }


}