package com.example.todolistapp.uiStates

import com.example.todolistapp.models.UserModel

// exhaustive (covers all scenario possibilities) interface
sealed interface UserDataStatusUIState {
    data class Success(val userModelData: UserModel): UserDataStatusUIState
    object Loading: UserDataStatusUIState
    object Start: UserDataStatusUIState
}