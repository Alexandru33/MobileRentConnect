package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.components.HeadingTextComponent
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.navigation.SystemBackButtonHandler
import ro.araducanu.rentconnect.ui.theme.BackgroundColor


@Composable
fun TermsAndConditionsScreen (){

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ){
        HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions))
        SystemBackButtonHandler {
            RentConnectAppRouter.navigateTo(Screen.SignUpScreen)
        }

    }
}

