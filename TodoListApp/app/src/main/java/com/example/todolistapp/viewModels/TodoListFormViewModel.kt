package com.example.todolistapp.viewModels

import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todolistapp.uiStates.TodoListFormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoListFormViewModel: ViewModel() {
    private val _todoListFormUIState = MutableStateFlow(TodoListFormUIState())

    val todoListFormUIState: StateFlow<TodoListFormUIState>
        get() {
            return _todoListFormUIState.asStateFlow()
        }

    var titleInput by mutableStateOf("")
        private set

    var descriptionInput by mutableStateOf("")
        private set

    var statusInput by mutableStateOf("")
        private set

    var priorityInput by mutableStateOf("")
        private set

    var dueDateInput by mutableStateOf("")
        private set

    fun changeTitleInput(title: String) {
        titleInput = title
    }

    fun changeDescriptionInput(description: String) {
        descriptionInput = description
    }

    fun changeStatusInput(status: String) {
        statusInput = status
    }

    fun changePriorityInput(priority: String) {
        priorityInput = priority
    }

    fun changeDueDateInput(dueDate: String) {
        dueDateInput = dueDate
    }

    fun changeStatusExpandedValue(
        expanded: Boolean
    ) {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                statusDropdownExpandedValue = expanded
            )
        }
    }

    fun dismissStatusDropdownMenu() {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                statusDropdownExpandedValue = false
            )
        }
    }

    fun changePriorityExpandedValue(
        expanded: Boolean
    ) {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                priorityDropdownExpandedValue = expanded
            )
        }
    }

    fun dismissPriorityDropdownMenu() {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                priorityDropdownExpandedValue = false
            )
        }
    }
}