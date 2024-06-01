package ro.araducanu.rentconnect.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ro.araducanu.rentconnect.components.MyBottomNavigationBarComponent
import ro.araducanu.rentconnect.components.MyBottomNavigationBarPreview
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.screens.AddScreen
import ro.araducanu.rentconnect.screens.ContractsScreen
import ro.araducanu.rentconnect.screens.LoginScreen
import ro.araducanu.rentconnect.screens.ProfileScreen
import ro.araducanu.rentconnect.screens.SearchScreen
import ro.araducanu.rentconnect.screens.SignUpScreen
import ro.araducanu.rentconnect.screens.StartScreen

import ro.araducanu.rentconnect.screens.TermsAndConditionsScreen
import ro.araducanu.rentconnect.screens.ViewingsScreen
import ro.araducanu.rentconnect.ui.theme.White


@Composable
fun RentConnectApp() {

    val selected = remember { mutableStateOf<ImageVector?>(null) }
    val currentScreen = RentConnectAppRouter.currentScreen.value


    LaunchedEffect(currentScreen) {
        selected.value = when (currentScreen) {
            is Screen.SearchScreen -> Icons.Default.Search
            is Screen.ViewingsScreen -> Icons.Default.CalendarMonth
            is Screen.AddScreen -> Icons.Default.Add
            is Screen.ContractsScreen -> Icons.Default.Article
            is Screen.ProfileScreen -> Icons.Default.Person
            else -> null
        }
    }


    Scaffold(
        bottomBar = {
            if (currentScreen !is Screen.StartScreen &&
                currentScreen !is Screen.SignUpScreen &&
                currentScreen !is Screen.LoginScreen
            )
                MyBottomNavigationBarComponent(selected)
        }
    )
    {innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            color = White
        ) {
            Crossfade(
                targetState = RentConnectAppRouter.currentScreen,
                label = ""
            ) { currentState ->
                when (currentState.value) {
                    is Screen.SignUpScreen -> {
                        SignUpScreen()
                    }

                    is Screen.TermsAndConditionsScreen -> {
                        TermsAndConditionsScreen()
                    }

                    is Screen.LoginScreen -> {
                        LoginScreen()
                    }

                    is Screen.SearchScreen -> {
                        SearchScreen()
                    }

                    is Screen.AddScreen -> {
                        AddScreen()
                    }

                    is Screen.ProfileScreen -> {
                        ProfileScreen()
                    }

                    is Screen.ViewingsScreen -> {
                        ViewingsScreen()
                    }

                    is Screen.ContractsScreen -> {
                        ContractsScreen()
                    }

                    is Screen.StartScreen -> {
                        StartScreen()
                    }


                }

            }
        }
    }
}

@Preview
@Composable
fun RentConnectAppPreview() {
    RentConnectApp()
}