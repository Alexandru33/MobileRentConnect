package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.components.ButtonComponent
import ro.araducanu.rentconnect.components.CheckboxComponent
import ro.araducanu.rentconnect.components.ClickableLoginTextComponent
import ro.araducanu.rentconnect.components.DividerTextComponent
import ro.araducanu.rentconnect.components.HeadingTextComponent
import ro.araducanu.rentconnect.components.MyTextField
import ro.araducanu.rentconnect.components.NormalTextComponent
import ro.araducanu.rentconnect.components.PasswordTextField
import ro.araducanu.rentconnect.data.signup.SignupUIEvent
import ro.araducanu.rentconnect.data.signup.SignupViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen

@Composable
fun SignUpScreen(signupViewModel: SignupViewModel = viewModel() ) {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(Color.White)
    ) {
        Column(  modifier = Modifier.fillMaxSize() ){
            NormalTextComponent(
                value = stringResource(id = R.string.welcome_message)
            )
            HeadingTextComponent(value = stringResource(R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(labelValue = stringResource(R.string.first_name) , painterResource(id = R.drawable.profile),
                onTextSelected = {
                    signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                errorStatus = signupViewModel.registrationUIState.value.firstNameError)

            MyTextField(labelValue = stringResource(R.string.last_name) , painterResource(id = R.drawable.profile),
                onTextSelected = {
                    signupViewModel.onEvent((SignupUIEvent.LastNameChanged(it)))
                },
                errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )
            MyTextField(labelValue = stringResource(R.string.e_mail) , painterResource(id = R.drawable.message),
                onTextSelected = {
                    signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                },
                errorStatus = signupViewModel.registrationUIState.value.emailError)
            PasswordTextField(labelValue = "Password" , painterResource(id = R.drawable.lock),
                onTextSelected = {
                    signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                },
                errorStatus = signupViewModel.registrationUIState.value.passwordError)
            CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions) ,
                onTextSelected = {
                    RentConnectAppRouter.navigateTo(Screen.TermsAndConditionsScreen)

                },
                onCheckedChange = {
                    signupViewModel.onEvent((SignupUIEvent.PrivacyPolicyCheckBoxClicked(it)))
                }
            )
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(
                value = stringResource(R.string.only_create_account),
                onButtonClicked = { signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked) },
                isEnabled = signupViewModel.allValidationsPassed.value)
            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()
            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = { RentConnectAppRouter.navigateTo(Screen.LoginScreen)})


        }

    }

}


@Preview(showBackground = true , showSystemUi = true, name = "Preview")
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}