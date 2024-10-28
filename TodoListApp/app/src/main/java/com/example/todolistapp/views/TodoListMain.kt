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
    val localContext = LocalContext.current

    NavHost(navController = navController, startDestination = PagesEnum.Login.name) {
        composable(route = PagesEnum.Login.name) {
            LoginView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }

        composable(route = PagesEnum.Register.name) {
            RegisterView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }

        composable(route = PagesEnum.Home.name) {
            HomeView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                homeViewModel = homeViewModel,
                navController = navController
            )
        }

        composable(route = PagesEnum.CreateTodo.name) {
            TodoListFormView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                context = localContext,
                todoListFormViewModel =  todoListFormViewModel,
                navController = navController
            )
        }

        composable(route = PagesEnum.TodoDetail.name) {
            TodoListDetailView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                navController = navController
            )
        }
    }
}