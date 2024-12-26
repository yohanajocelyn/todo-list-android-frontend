package com.example.todolistapp.uiStates

import com.example.todolistapp.models.UserModel

sealed interface StringDataStatusUIState {
    data class Success(val message: String): StringDataStatusUIState
    object Loading: StringDataStatusUIState
    object Start: StringDataStatusUIState
    data class Failed(val errorMessage: String): StringDataStatusUIState
}