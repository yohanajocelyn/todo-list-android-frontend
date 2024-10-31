package com.example.todolistapp.uiStates

import com.example.todolistapp.models.GeneralResponseModel
import com.example.todolistapp.models.UserModel

sealed interface LogoutStatusUIState {
    data class Success(val responseData: String): LogoutStatusUIState
    object Loading: LogoutStatusUIState
    object Start: LogoutStatusUIState
}