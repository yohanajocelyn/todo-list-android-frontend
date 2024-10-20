package com.example.todolistapp.views

import android.graphics.pdf.PdfDocument.Page
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.enums.PagesEnum

@Composable
fun TodoListApp(
    navController: NavHostController = rememberNavController()
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
                }
            )
        }

        composable(route = PagesEnum.Register.name) {
            RegisterView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onSignInTextClicked = {
                    navController.navigate(PagesEnum.Login.name) {
                        popUpTo(PagesEnum.Register.name) {
                            inclusive = true
                        }
                    }
                },
                focusManager = focusManager,
                onSignUpButtonClicked = {
                    navController.navigate(PagesEnum.Home.name) {
                        popUpTo(PagesEnum.Register.name) {
                            inclusive = true
                        }
                    }
                }
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
                    .background(Color.White)
            )
        }

        composable(route = PagesEnum.CreateTodo.name) {
            TodoListFormView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                context = localContext,
                onCancelButtonClick = {
                    // TODO: Add on cancel button click event handler
                },
                onSaveButtonClick = {
                    // TODO: Add on save button click event handler
                }
            )
        }
    }
}