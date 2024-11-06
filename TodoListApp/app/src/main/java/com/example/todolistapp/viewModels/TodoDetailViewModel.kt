package com.example.todolistapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todolistapp.TodoListApplication
import com.example.todolistapp.enums.PagesEnum
import com.example.todolistapp.models.ErrorModel
import com.example.todolistapp.models.GeneralResponseModel
import com.example.todolistapp.models.GetTodoResponse
import com.example.todolistapp.models.TodoModel
import com.example.todolistapp.repositories.TodoRepository
import com.example.todolistapp.uiStates.StringDataStatusUIState
import com.example.todolistapp.uiStates.TodoDetailDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class TodoDetailViewModel(
    private val todoRepository: TodoRepository
): ViewModel() {
    var dataStatus: TodoDetailDataStatusUIState by mutableStateOf(TodoDetailDataStatusUIState.Start)
        private set

    var deleteStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
        private set

    fun getTodo(token: String, todoId: Int, navController: NavHostController, isUpdating: Boolean) {
        viewModelScope.launch {
            dataStatus = TodoDetailDataStatusUIState.Loading

            try {
                val call = todoRepository.getTodo(token, todoId)

                call.enqueue(object: Callback<GetTodoResponse> {
                    override fun onResponse(call: Call<GetTodoResponse>, res: Response<GetTodoResponse>) {
                        if (res.isSuccessful) {
                            dataStatus = TodoDetailDataStatusUIState.Success(res.body()!!.data)

                            Log.d("get-todo-result", "GET TODO: ${res.body()}")

                            if (isUpdating) {
                                navController.popBackStack()
                            } else {
                                navController.navigate(PagesEnum.TodoDetail.name) {
                                    popUpTo(PagesEnum.Home.name) {
                                        inclusive = false
                                    }
                                }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            dataStatus = TodoDetailDataStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<GetTodoResponse>, t: Throwable) {
                        dataStatus = TodoDetailDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                dataStatus = TodoDetailDataStatusUIState.Failed(error.localizedMessage)
            }


        }
    }

    fun deleteTodo(token: String, todoId: Int, navController: NavHostController) {
        viewModelScope.launch {
            deleteStatus = StringDataStatusUIState.Loading

            try {
                val call = todoRepository.deleteTodo(token, todoId)

                call.enqueue(object: Callback<GeneralResponseModel> {
                    override fun onResponse(
                        call: Call<GeneralResponseModel>,
                        res: Response<GeneralResponseModel>
                    ) {
                        if (res.isSuccessful) {
                            deleteStatus = StringDataStatusUIState.Success(res.body()!!.data)

                            Log.d("delete-status", "Delete status: ${res.body()!!.data}")

                            navController.popBackStack()
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            deleteStatus = StringDataStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        deleteStatus = StringDataStatusUIState.Failed(t.localizedMessage)
                    }

                })
            } catch (error: IOException) {
                deleteStatus = StringDataStatusUIState.Failed(error.localizedMessage)
            }
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

    fun clearErrorMessage() {
        deleteStatus = StringDataStatusUIState.Start
    }
}