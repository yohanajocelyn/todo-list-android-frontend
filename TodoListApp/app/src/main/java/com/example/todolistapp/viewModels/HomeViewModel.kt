package com.example.todolistapp.viewModels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todolistapp.enums.PrioritiesEnum
import com.example.todolistapp.models.TodoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _todoModel = MutableStateFlow<MutableList<TodoModel>>(mutableListOf())

    val todoModel: StateFlow<List<TodoModel>>
        get() {
            return _todoModel.asStateFlow()
        }

    init {
        _todoModel.value.add(
           TodoModel(
               title = "hello world",
               description = "alskdjfk;ashdpfuihqwpeiuhf",
               priority = PrioritiesEnum.High,
               dueDate = "12 October 2022",
               status = "On Going"
           )
        )
    }

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