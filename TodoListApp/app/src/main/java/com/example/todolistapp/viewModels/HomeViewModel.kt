package com.example.todolistapp.viewModels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.todolistapp.TodoListApplication
import com.example.todolistapp.enums.PagesEnum
import com.example.todolistapp.enums.PrioritiesEnum
import com.example.todolistapp.models.GeneralResponseModel
import com.example.todolistapp.models.TodoModel
import com.example.todolistapp.models.UserModel
import com.example.todolistapp.repositories.UserRepository
import com.example.todolistapp.uiStates.AuthenticationUIState
import com.example.todolistapp.uiStates.HomeUIState
import com.example.todolistapp.uiStates.LogoutStatusUIState
import com.example.todolistapp.uiStates.UserDataStatusUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _todoModel = MutableStateFlow<MutableList<TodoModel>>(mutableListOf())

    val todoModel: StateFlow<List<TodoModel>>
        get() {
            return _todoModel.asStateFlow()
        }

    private val _homeUIState = MutableStateFlow(HomeUIState())

    val homeUIState: StateFlow<HomeUIState>
        get() {
            return _homeUIState.asStateFlow()
        }

    var dataStatus: LogoutStatusUIState by mutableStateOf(LogoutStatusUIState.Start)

    val username: StateFlow<String> = userRepository.currentUsername.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val token: StateFlow<String> = userRepository.currentUserToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

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

    fun logoutUser(token: String, navController: NavHostController) {
        viewModelScope.launch {
            dataStatus = LogoutStatusUIState.Loading

            try {
                val call = userRepository.logout(token)

                call.enqueue(object: Callback<GeneralResponseModel>{
                    override fun onResponse(call: Call<GeneralResponseModel>, res: Response<GeneralResponseModel>) {
                        if (res.isSuccessful) {
                            dataStatus = LogoutStatusUIState.Success(responseData = res.body()!!.data)

                            navController.navigate(PagesEnum.Login.name) {
                                popUpTo(PagesEnum.Home.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            dataStatus = LogoutStatusUIState.Start
                            // set error message toast
                            displayErrorMessage(res.body()!!.errors)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        dataStatus = LogoutStatusUIState.Start
                        Log.d("logout-failure", t.localizedMessage)
                        displayErrorMessage(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                dataStatus = LogoutStatusUIState.Start
                Log.d("logout-error", error.localizedMessage)
                displayErrorMessage(error.localizedMessage)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoListApplication)
                val userRepository = application.container.userRepository
                HomeViewModel(userRepository)
            }
        }
    }

    fun displayErrorMessage(message: String) {
        _homeUIState.update { currentState ->
            currentState.copy(
                errorMessage = message
            )
        }
    }
}