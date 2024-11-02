package com.example.todolistapp.services

import com.example.todolistapp.models.GeneralResponseModel
import com.example.todolistapp.models.TodoModel
import com.example.todolistapp.models.TodoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TodoAPIService {
    @GET("api/todo-list")
    fun getAllTodos(@Header("X-API-TOKEN") token: String): Call<TodoResponse>

    @POST("api/todo-list")
    fun createTodo(@Header("X-API-TOKEN") token: String, @Body todoModel: TodoModel): Call<GeneralResponseModel>
}