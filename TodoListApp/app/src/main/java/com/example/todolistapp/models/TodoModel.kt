package com.example.todolistapp.models

import com.example.todolistapp.enums.PrioritiesEnum
import com.google.gson.annotations.SerializedName

data class TodoResponse(
    val data: List<TodoModel>
)

data class TodoModel (
    val title: String = "",
    val status: String = "",
    val priority: String = "",
    val description: String = "",

    @SerializedName("due_date")
    val dueDate: String = ""
)