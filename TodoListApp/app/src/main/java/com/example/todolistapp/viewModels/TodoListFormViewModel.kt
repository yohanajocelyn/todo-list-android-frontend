package com.example.todolistapp.viewModels

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todolistapp.uiStates.TodoListFormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.Date

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

    fun showDatePickerDialog(datePickerDialog: DatePickerDialog) {
        datePickerDialog.show()
    }

    fun initDatePickerDialog(context: Context): DatePickerDialog {
        // Initializing a Calendar
        val datePickerCalendar = Calendar.getInstance()

        // Fetching current year, month and day
        val calYear = datePickerCalendar.get(Calendar.YEAR)
        val calMonth = datePickerCalendar.get(Calendar.MONTH)
        val calDay = datePickerCalendar.get(Calendar.DAY_OF_MONTH)

        datePickerCalendar.time = Date()

        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, calYear: Int, calMonth: Int, calDay: Int ->
                dueDateInput = "$calDay/${calMonth + 1}/$calYear"
            }, calYear, calMonth, calDay
        )

        return datePickerDialog
    }
}