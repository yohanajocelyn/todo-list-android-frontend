package com.example.todolistapp.uiStates

import androidx.compose.ui.text.input.VisualTransformation
import com.example.todolistapp.R

data class AuthenticationUIState(
    val showPassword: Boolean = false,
    val passwordVisibility: VisualTransformation = VisualTransformation.None,
    val passwordVisibilityIcon: Int = R.drawable.ic_password_visible
)