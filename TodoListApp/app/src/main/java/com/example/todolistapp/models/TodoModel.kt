package com.example.todolistapp.models

import com.example.todolistapp.enums.PrioritiesEnum

data class TodoModel (
    val title: String = "",
    val status: String = "",
    val priority: PrioritiesEnum = PrioritiesEnum.Low,
    val description: String = "",
    val dueDate: String = ""
)