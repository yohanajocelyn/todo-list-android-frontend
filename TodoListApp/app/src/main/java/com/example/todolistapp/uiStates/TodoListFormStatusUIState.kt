package com.example.todolistapp.uiStates

sealed interface TodoListFormStatusUIState {
    data class Success(val data: String): TodoListFormStatusUIState
    object Start: TodoListFormStatusUIState
    object Loading: TodoListFormStatusUIState
    data class Failed(val errorMessage: String): TodoListFormStatusUIState
}