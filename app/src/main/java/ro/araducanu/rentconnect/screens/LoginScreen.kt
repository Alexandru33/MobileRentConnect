package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.araducanu.rentconnect.components.ButtonComponent
import ro.araducanu.rentconnect.components.DividerTextComponent
import ro.araducanu.rentconnect.components.HeadingTextComponent
import ro.araducanu.rentconnect.components.MyTextField
import ro.araducanu.rentconnect.components.NormalTextComponent
import ro.araducanu.rentconnect.components.PasswordTextField
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.navigation.SystemBackButtonHandler
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.components.ClickableLoginTextComponent
import ro.araducanu.rentconnect.components.UnderLinedTextComponent

@Composable
fun LoginScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome_back))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextField(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),

                )

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),

                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {

                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    RentConnectAppRouter.navigateTo(Screen.SignUpScreen)
                })
            }
        }


    }

}



@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}