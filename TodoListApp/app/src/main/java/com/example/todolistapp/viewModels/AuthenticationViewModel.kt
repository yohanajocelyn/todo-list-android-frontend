package com.example.todolistapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.todolistapp.R
import com.example.todolistapp.TodoListApplication
import com.example.todolistapp.enums.PagesEnum
import com.example.todolistapp.models.UserResponse
import com.example.todolistapp.repositories.AuthenticationRepository
import com.example.todolistapp.repositories.NetworkUserRepository
import com.example.todolistapp.repositories.UserRepository
import com.example.todolistapp.uiStates.AuthenticationUIState
import com.example.todolistapp.uiStates.UserDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _authenticationUIState = MutableStateFlow(AuthenticationUIState())

    val authenticationUIState: StateFlow<AuthenticationUIState>
        get() {
            return _authenticationUIState.asStateFlow()
        }

    var dataStatus: UserDataStatusUIState by mutableStateOf(UserDataStatusUIState.Start)

    var usernameInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    var confirmPasswordInput by mutableStateOf("")
        private set

    var emailInput by mutableStateOf("")
        private set

    fun changeEmailInput(emailInput: String) {
        this.emailInput = emailInput
    }

    fun changeConfirmPasswordInput(confirmPasswordInput: String) {
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
            if (currentState.showPassword) {
                currentState.copy(
                    showPassword = false,
                    passwordVisibility = PasswordVisualTransformation(),
                    passwordVisibilityIcon = R.drawable.ic_password_visible
                )
            } else {
                currentState.copy(
                    showPassword = true,
                    passwordVisibility = VisualTransformation.None,
                    passwordVisibilityIcon = R.drawable.ic_password_invisible
                )
            }
        }
    }

    fun changeConfirmPasswordVisibility() {
        _authenticationUIState.update { currentState ->
            if (currentState.showConfirmPassword) {
                currentState.copy(
                    showConfirmPassword = false,
                    confirmPasswordVisibility = PasswordVisualTransformation(),
                    confirmPasswordVisibilityIcon = R.drawable.ic_password_visible
                )
            } else {
                currentState.copy(
                    showConfirmPassword = true,
                    confirmPasswordVisibility = VisualTransformation.None,
                    confirmPasswordVisibilityIcon = R.drawable.ic_password_invisible
                )
            }
        }
    }

    fun checkLoginForm() {
        if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
            _authenticationUIState.update { currentState ->
                currentState.copy(
                    buttonEnabled = true
                )
            }
        } else {
            _authenticationUIState.update { currentState ->
                currentState.copy(
                    buttonEnabled = false
                )
            }
        }
    }

    fun checkRegisterForm() {
        if (emailInput.isNotEmpty() && passwordInput.isNotEmpty() && usernameInput.isNotEmpty() && confirmPasswordInput.isNotEmpty() && passwordInput == confirmPasswordInput) {
            _authenticationUIState.update { currentState ->
                currentState.copy(
                    buttonEnabled = true
                )
            }
        } else {
            _authenticationUIState.update { currentState ->
                currentState.copy(
                    buttonEnabled = false
                )
            }
        }
    }

    fun checkButtonEnabled(isEnabled: Boolean): Color {
        if (isEnabled) {
            return Color.Blue
        }

        return Color.LightGray
    }

    fun registerUser(navController: NavHostController) {
        viewModelScope.launch {
            dataStatus = UserDataStatusUIState.Loading

            try {
                val call = authenticationRepository.register(usernameInput, emailInput, passwordInput)
//                dataStatus = UserDataStatusUIState.Success(registerResult)

                call.enqueue(object: Callback<UserResponse>{
                    override fun onResponse(call: Call<UserResponse>, res: Response<UserResponse>) {
                        if (res.isSuccessful) {
                            Log.d("response-data", "RESPONSE DATA: ${res.body()}")

                            saveUsernameToken(res.body()!!.data.token!!, res.body()!!.data.username)

                            dataStatus = UserDataStatusUIState.Success(res.body()!!.data)

                            navController.navigate(PagesEnum.Home.name) {
                                popUpTo(PagesEnum.Register.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            // get error message
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                UserResponse::class.java
                            )

                            displayErrorMessage(errorMessage.errors)

                            Log.d("error-data", "ERROR DATA: ${errorMessage}")
                            dataStatus = UserDataStatusUIState.Start
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.d("error-data", "ERROR DATA: ${t.localizedMessage}")
                        dataStatus = UserDataStatusUIState.Start
                        displayErrorMessage(t.localizedMessage)
                    }

                })
            } catch (error: IOException) {
                dataStatus = UserDataStatusUIState.Start
                Log.d("register-error", "REGISTER ERROR: ${error.localizedMessage}")
                displayErrorMessage(error.localizedMessage)
            }
        }
    }

    fun loginUser(
        navController: NavHostController
    ) {
        viewModelScope.launch {
            dataStatus = UserDataStatusUIState.Loading
            try {
                val call = authenticationRepository.login(emailInput, passwordInput)
                call.enqueue(object: Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, res: Response<UserResponse>) {
                        if (res.isSuccessful) {
                            saveUsernameToken(res.body()!!.data.token!!, res.body()!!.data.username)

                            dataStatus = UserDataStatusUIState.Success(res.body()!!.data)

                            navController.navigate(PagesEnum.Home.name) {
                                popUpTo(PagesEnum.Login.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                UserResponse::class.java
                            )

                            displayErrorMessage(errorMessage.errors)

                            Log.d("error-data", "ERROR DATA: ${errorMessage.errors}")
                            dataStatus = UserDataStatusUIState.Start
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        dataStatus = UserDataStatusUIState.Start

                        displayErrorMessage(t.localizedMessage)
                    }

                })
            } catch (error: IOException) {
                dataStatus = UserDataStatusUIState.Start
                Log.d("register-error", "LOGIN ERROR: ${error.toString()}")

                displayErrorMessage(error.localizedMessage)
            }
        }
    }

    fun saveUsernameToken(token: String, username: String) {
        viewModelScope.launch {
            userRepository.saveUserToken(token)
            userRepository.saveUsername(username)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoListApplication)
                val authenticationRepository = application.container.authenticationRepository
                val userRepository = application.container.userRepository
                AuthenticationViewModel(authenticationRepository, userRepository)
            }
        }
    }

    fun resetViewModel() {
        changeEmailInput("")
        changePasswordInput("")
        changeUsernameInput("")
        changeConfirmPasswordInput("")
        _authenticationUIState.update { currentState ->
            currentState.copy(
                showConfirmPassword = false,
                showPassword = false,
                passwordVisibility = PasswordVisualTransformation(),
                confirmPasswordVisibility = PasswordVisualTransformation(),
                passwordVisibilityIcon = R.drawable.ic_password_visible,
                confirmPasswordVisibilityIcon = R.drawable.ic_password_visible,
                buttonEnabled = false,
                errorMessage = null
            )
        }
    }

    fun displayErrorMessage(message: String) {
        _authenticationUIState.update { currentState ->
            currentState.copy(
                errorMessage = message
            )
        }
    }

    fun clearErrorMessage() {
        _authenticationUIState.update { currentState ->
            currentState.copy(
                errorMessage = null
            )
        }
    }
}