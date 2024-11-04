package com.example.todolistapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolistapp.TodoListApplication
import com.example.todolistapp.repositories.TodoRepository
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    todoRepository: TodoRepository
): ViewModel() {
    var dataStatus:

    fun getTodo() {
        viewModelScope.launch {

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoListApplication)
                val todoRepository = application.container.todoRepository
                TodoDetailViewModel(todoRepository)
            }
        }
    }
}