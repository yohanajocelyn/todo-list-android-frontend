package com.example.todolistapp.viewModels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todolistapp.enums.PrioritiesEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    fun changePriorityTextBackgroundColor(
        priority: PrioritiesEnum
    ): Color {
        if (priority == PrioritiesEnum.High) {
            return Color.Red
        } else if (priority == PrioritiesEnum.Medium) {
            return Color.Yellow
        }

        return Color.Green
    }
}