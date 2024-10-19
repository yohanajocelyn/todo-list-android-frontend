package com.example.todolistapp.uiStates

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import java.util.Locale

data class TodoListFormUIState (
    val statusDropdownExpandedValue: Boolean = false,
    val priorityDropdownExpandedValue: Boolean = false,
    val statusDropdownItems: List<String> = listOf("To Do", "On Going", "Finished"),
    val priorityDropdownItems: List<String> = listOf("High", "Medium", "Low"),
)