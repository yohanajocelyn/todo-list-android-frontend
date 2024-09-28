package com.example.todolistapp.views.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.enums.PagesEnum

@Composable
fun TodoListApp(
    navController: NavHostController = rememberNavController()
) {
    // TODO: finish the routers
    NavHost(navController = navController, startDestination = PagesEnum.Login.name) {
        composable(route = PagesEnum.Login.name) {
            LoginView()
        }

        composable(route = PagesEnum.Register.name) {
            RegisterView()
        }
    }
}