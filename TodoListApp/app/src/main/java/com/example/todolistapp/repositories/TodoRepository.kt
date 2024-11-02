package com.example.todolistapp.repositories

import com.example.todolistapp.enums.PrioritiesEnum
import com.example.todolistapp.models.GeneralResponseModel
import com.example.todolistapp.models.TodoModel
import com.example.todolistapp.models.TodoResponse
import com.example.todolistapp.services.TodoAPIService
import retrofit2.Call

interface TodoRepository {
    fun getAllTodos(token: String): Call<TodoResponse>

    fun createTodo(token: String, title: String, description: String, dueDate: String, status: String, priority: String): Call<GeneralResponseModel>
}

class NetworkTodoRepository(
    private val todoAPIService: TodoAPIService
): TodoRepository {
    override fun getAllTodos(token: String): Call<TodoResponse> {
        return todoAPIService.getAllTodos(token)
    }

    override fun createTodo(
        token: String,
        title: String,
        description: String,
        dueDate: String,
        status: String,
        priority: String
    ): Call<GeneralResponseModel> {
        return todoAPIService.createTodo(
            token,
            TodoModel(title, status, priority, description, dueDate)
        )
    }
}