package com.example.todolistapp.views

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.R
import com.example.todolistapp.ui.theme.TodoListAppTheme
import com.example.todolistapp.viewModels.AuthenticationViewModel
import com.example.todolistapp.views.templates.AuthenticationButton
import com.example.todolistapp.views.templates.AuthenticationQuestion
import com.example.todolistapp.views.templates.GeneralOutlinedTextField
import com.example.todolistapp.views.templates.PasswordOutlinedTextField

@Composable
fun LoginView(
    authenticationViewModel: AuthenticationViewModel = viewModel(),
    onSignUpTextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val loginUIState by authenticationViewModel.authenticationUIState.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "WELCOME BACK TO",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "TODO LIST",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GeneralOutlinedTextField(
                usernameInput = authenticationViewModel.usernameInput,
                onUsernameInputValueChange = {
                    authenticationViewModel.changeUsernameInput(it)
                },
                labelText = stringResource(id = R.string.usernameText),
                placeholderText = stringResource(id = R.string.usernameText),
                leadingIconSrc = painterResource(id = R.drawable.ic_username),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(5.dp))

            PasswordOutlinedTextField(
                passwordInput = authenticationViewModel.passwordInput,
                onPasswordInputValueChange = {
                    authenticationViewModel.changePasswordInput(it)
                },
                passwordVisibilityIcon = painterResource(id = loginUIState.passwordVisibilityIcon),
                labelText = stringResource(id = R.string.passwordText),
                placeholderText = stringResource(id = R.string.passwordText),
                onTrailingIconClick = {
                    authenticationViewModel.changePasswordVisibility()
                },
                passwordVisibility = loginUIState.passwordVisibility,
                modifier = Modifier
                    .fillMaxWidth()
            )

            AuthenticationButton(
                buttonText = stringResource(id = R.string.loginText),
                onButtonClick = {

                },
                buttonModifier = Modifier
                    .padding(top = 30.dp),
                textModifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 15.dp)
            )
        }

        AuthenticationQuestion(
            questionText = stringResource(id = R.string.don_t_have_an_account_yet_text),
            actionText = stringResource(id = R.string.sign_up_text),
            onActionTextClicked = onSignUpTextClicked,
            rowModifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun LoginViewPreview() {
    TodoListAppTheme {
        LoginView(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            onSignUpTextClicked = {

            }
        )
    }
}