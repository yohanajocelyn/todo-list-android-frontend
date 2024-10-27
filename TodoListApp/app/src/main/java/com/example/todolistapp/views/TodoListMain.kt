package com.example.todolistapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.enums.PagesEnum
import com.example.todolistapp.viewModels.AuthenticationViewModel
import com.example.todolistapp.viewModels.HomeViewModel
import com.example.todolistapp.viewModels.TodoListFormViewModel

@Composable
fun TodoListApp(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = viewModel(),
    todoListFormViewModel: TodoListFormViewModel = viewModel(),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory)
) {
    val focusManager = LocalFocusManager.current
    val localContext = LocalContext.current

    NavHost(navController = navController, startDestination = PagesEnum.Login.name) {
        composable(route = PagesEnum.Login.name) {
            LoginView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                onSignUpTextClicked = {
                    authenticationViewModel.resetViewModel()
                    navController.navigate(PagesEnum.Register.name) {
                        popUpTo(PagesEnum.Login.name) {
                            inclusive = true
                        }
                    }
                },
                focusManager = focusManager,
                onSignInButtonClicked = {
                    navController.navigate(PagesEnum.Home.name) {
                        popUpTo(PagesEnum.Login.name) {
                            inclusive = true
                        }
                    }
                },
                authenticationViewModel = authenticationViewModel
            )
        }

        composable(route = PagesEnum.Register.name) {
            RegisterView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                onSignInTextClicked = {
                    authenticationViewModel.resetViewModel()
                    navController.navigate(PagesEnum.Login.name) {
                        popUpTo(PagesEnum.Register.name) {
                            inclusive = true
                        }
                    }
                },
                focusManager = focusManager,
                onSignUpButtonClicked = {
                    authenticationViewModel.registerUser(navController)
                },
                authenticationViewModel = authenticationViewModel
            )
        }

        composable(route = PagesEnum.Home.name) {
            HomeView(
                onAddButtonClicked = {
                    // TODO: Buat kelas CreateTodoView
                    navController.navigate(PagesEnum.CreateTodo.name) {
                        popUpTo(PagesEnum.Home.name) {
                            inclusive = false
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                homeViewModel = homeViewModel,
                onCardButtonClick = {
                    navController.navigate(PagesEnum.TodoDetail.name) {
                        popUpTo(PagesEnum.Home.name) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable(route = PagesEnum.CreateTodo.name) {
            TodoListFormView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                context = localContext,
                onCancelButtonClick = {
                    navController.popBackStack()
                },
                onSaveButtonClick = {
                    // TODO: Add on save button click event handler
                    navController.navigate(PagesEnum.Home.name) {
                        popUpTo(PagesEnum.CreateTodo.name) {
                            inclusive = true
                        }
                    }
                },
                todoListFormViewModel =  todoListFormViewModel
            )
        }

        composable(route = PagesEnum.TodoDetail.name) {
            TodoListDetailView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onEditButtonClick = {
                    navController.navigate(PagesEnum.CreateTodo.name) {
                        popUpTo(PagesEnum.TodoDetail.name) {
                            inclusive = false
                        }
                    }
                },
                onDeleteButtonClick = {

                }
            )
        }
    }
}