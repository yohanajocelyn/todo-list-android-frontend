package com.example.todolistapp.views.composables

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.R
import com.example.todolistapp.enums.PagesEnum
import com.example.todolistapp.viewModels.AuthenticationViewModel

@Composable
fun LoginView(
    authenticationViewModel: AuthenticationViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val loginUIState by authenticationViewModel.authenticationUIState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly
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
            OutlinedTextField(
                value = authenticationViewModel.usernameInput,
                onValueChange = {
                    authenticationViewModel.changeUsernameInput(it)
                },
                singleLine = true,
                label = {
                    Text(
                        text = stringResource(R.string.usernameText)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.usernameText)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(size = 15.dp),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_username),
                        contentDescription = null
                    )
                }
            )

            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                value = authenticationViewModel.passwordInput,
                onValueChange = {
                    authenticationViewModel.changePasswordInput(it)
                },
                singleLine = true,
                label = {
                    Text(
                        text = stringResource(R.string.passwordText)
                    )
                },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = loginUIState.passwordVisibilityIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                authenticationViewModel.changePasswordVisibility()
                            }
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.passwordText)
                    )
                },
                visualTransformation = loginUIState.passwordVisibility,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(size = 15.dp),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = null
                    )
                }
            )

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(top = 30.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(
                    text = stringResource(R.string.loginText),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 15.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .align(
                    Alignment.CenterHorizontally
                )
        ) {
            Text(
                text = stringResource(R.string.don_t_have_an_account_yet_text)
            )

            Text(
                text = stringResource(R.string.sign_up_text),
                color = Color.Blue
            )
        }
    }

}