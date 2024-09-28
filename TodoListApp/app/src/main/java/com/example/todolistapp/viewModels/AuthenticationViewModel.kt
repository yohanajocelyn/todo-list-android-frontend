package com.example.todolistapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import com.example.todolistapp.uiStates.AuthenticationUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.todolistapp.R

class AuthenticationViewModel: ViewModel() {
    private val _authenticationUIState = MutableStateFlow(AuthenticationUIState())

    val authenticationUIState: StateFlow<AuthenticationUIState>
        get() {
            return _authenticationUIState.asStateFlow()
        }

    var usernameInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    var confirmPasswordInput by mutableStateOf("")
        private set

    fun changeConfirmPassword(confirmPasswordInput: String) {
        this.confirmPasswordInput = confirmPasswordInput
    }

    fun changeUsernameInput(usernameInput: String) {
        this.usernameInput = usernameInput
    }

    fun changePasswordInput(passwordInput: String) {
        this.passwordInput = passwordInput
    }

    fun changePasswordVisibility() {
        _authenticationUIState.update { currentState ->
            if(currentState.showPassword) {
                currentState.copy(
                    showPassword = false,
                    passwordVisibility = VisualTransformation.None,
                    passwordVisibilityIcon = R.drawable.ic_password_visible
                )
            } else {
                currentState.copy(
                    showPassword = true,
                    passwordVisibility = PasswordVisualTransformation(),
                    passwordVisibilityIcon = R.drawable.ic_password_invisible
                )
            }
        }
    }
}