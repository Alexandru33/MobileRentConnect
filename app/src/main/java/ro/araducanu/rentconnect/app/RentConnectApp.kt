package ro.araducanu.rentconnect.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.screens.LoginScreen
import ro.araducanu.rentconnect.screens.SignUpScreen

import ro.araducanu.rentconnect.screens.TermsAndConditionsScreen
import ro.araducanu.rentconnect.ui.theme.White


@Composable
fun RentConnectApp() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ){
        Crossfade(targetState = RentConnectAppRouter.currentScreen, label = "") {
            currentState -> when(currentState.value){
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }
            }
            
        }
    }
}