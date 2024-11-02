package com.example.todolistapp.viewModels

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
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
import com.example.todolistapp.models.ErrorModel
import com.example.todolistapp.models.GeneralResponseModel
import com.example.todolistapp.repositories.TodoRepository
import com.example.todolistapp.repositories.UserRepository
import com.example.todolistapp.uiStates.TodoListFormStatusUIState
import com.example.todolistapp.uiStates.TodoListFormUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Calendar
import java.util.Date

class TodoListFormViewModel(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _todoListFormUIState = MutableStateFlow(TodoListFormUIState())

    val todoListFormUIState: StateFlow<TodoListFormUIState>
        get() {
            return _todoListFormUIState.asStateFlow()
        }

    val token: StateFlow<String> = userRepository.currentUserToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    var submissionStatus: TodoListFormStatusUIState by mutableStateOf(TodoListFormStatusUIState.Start)
        private set

    var titleInput by mutableStateOf("")
        private set

    var descriptionInput by mutableStateOf("")
        private set

    var statusInput by mutableStateOf("")
        private set

    var priorityInput: String by mutableStateOf(PrioritiesEnum.High.name)
        private set

    var dueDateInput by mutableStateOf("")
        private set

    fun changeTitleInput(title: String) {
        titleInput = title
    }

    fun changeDescriptionInput(description: String) {
        descriptionInput = description
    }

    fun changeStatusInput(status: String) {
        statusInput = status
    }

    fun changePriorityInput(priority: String) {
        priorityInput = priority
    }

    fun changeStatusExpandedValue(
        expanded: Boolean
    ) {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                statusDropdownExpandedValue = expanded
            )
        }
    }

    fun dismissStatusDropdownMenu() {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                statusDropdownExpandedValue = false
            )
        }
    }

    fun changePriorityExpandedValue(
        expanded: Boolean
    ) {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                priorityDropdownExpandedValue = expanded
            )
        }
    }

    fun dismissPriorityDropdownMenu() {
        _todoListFormUIState.update { currentState ->
            currentState.copy(
                priorityDropdownExpandedValue = false
            )
        }
    }

    fun showDatePickerDialog(datePickerDialog: DatePickerDialog) {
        datePickerDialog.show()
    }

    fun initDatePickerDialog(context: Context): DatePickerDialog {
        // Initializing a Calendar
        val datePickerCalendar = Calendar.getInstance()

        // Fetching current year, month and day
        val calYear = datePickerCalendar.get(Calendar.YEAR)
        val calMonth = datePickerCalendar.get(Calendar.MONTH)
        val calDay = datePickerCalendar.get(Calendar.DAY_OF_MONTH)

        datePickerCalendar.time = Date()

        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, calYear: Int, calMonth: Int, calDay: Int ->
                dueDateInput = "$calDay/${calMonth + 1}/$calYear"
                checkNullFormValues()
            }, calYear, calMonth, calDay
        )

        return datePickerDialog
    }

    fun checkNullFormValues() {
        if (titleInput.isNotEmpty() && descriptionInput.isNotEmpty() && statusInput.isNotEmpty() && dueDateInput.isNotEmpty()) {
            _todoListFormUIState.update { currentState ->
                currentState.copy(
                    saveButtonEnabled = true
                )
            }
        } else {
            _todoListFormUIState.update { currentState ->
                currentState.copy(
                    saveButtonEnabled = false
                )
            }
        }
    }

    fun changeSaveButtonColor(): Color {
        if (_todoListFormUIState.value.saveButtonEnabled) {
            return Color.Blue
        }

        return Color.LightGray
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoListApplication)
                val todoRepository = application.container.todoRepository
                val userRepository = application.container.userRepository
                TodoListFormViewModel(todoRepository, userRepository)
            }
        }
    }

    fun createTodo(navController: NavHostController, token: String) {
        viewModelScope.launch {
            submissionStatus = TodoListFormStatusUIState.Loading

            Log.d("token-todo-list-form", "TOKEN: ${token}")

            try {
                val call = todoRepository.createTodo(token = token , title = titleInput, status = statusInput, description = descriptionInput, dueDate = dueDateInput, priority = priorityInput)

                call.enqueue(object: Callback<GeneralResponseModel> {
                    override fun onResponse(
                        call: Call<GeneralResponseModel>,
                        res: Response<GeneralResponseModel>
                    ) {
                        if (res.isSuccessful) {
                            Log.d("json", "JSON RESPONSE: ${res.body()!!.data}")
                            submissionStatus = TodoListFormStatusUIState.Success(res.body()!!.data)

                            navController.navigate(PagesEnum.Home.name) {
                                popUpTo(PagesEnum.CreateTodo.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            submissionStatus = TodoListFormStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        submissionStatus = TodoListFormStatusUIState.Failed(t.localizedMessage)
                    }

                })
            } catch (error: IOException) {
                submissionStatus = TodoListFormStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    fun clearErrorMessage() {
        submissionStatus = TodoListFormStatusUIState.Start
    }
}