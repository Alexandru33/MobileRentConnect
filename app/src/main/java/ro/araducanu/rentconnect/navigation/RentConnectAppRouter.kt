package ro.araducanu.rentconnect.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Stack


sealed class Screen {
    object PropertyModifyScreen : Screen()

    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()

    object LoginScreen : Screen()
    object AddScreen : Screen()
    object ProfileScreen : Screen()
    object SearchScreen : Screen()
    object ViewingsScreen : Screen()
    object ContractsScreen : Screen()
    object StartScreen : Screen()

    object AddNewDetailsScreen : Screen()

    object PropertyDetailScreen : Screen()

    object PropertyEditScreen : Screen()


}
object RentConnectAppRouter {

    private val screenStack = Stack<Screen>().apply{
        push(Screen.SearchScreen)
    }


    var currentScreen : MutableState<Screen> = mutableStateOf(Screen.StartScreen)
    var previousScreen : Screen? = null

    fun makeScreenStackEmpty() {
        while ( !screenStack.isEmpty() )
        {
            screenStack.pop()
        }

        screenStack.push(Screen.StartScreen)
    }

    fun navigateTo( destination : Screen)
    {
        previousScreen = screenStack.peek()
        screenStack.push(destination)
        currentScreen.value = destination
    }

    fun navigateBack() {
        if (currentScreen.value is Screen.SearchScreen) {
            val tempStack = screenStack.clone() as Stack<*>
            tempStack.pop()
            if (!tempStack.isEmpty() && tempStack.peek() is Screen.LoginScreen) {
                return
            }
        }
        if (screenStack.size > 1) {
            screenStack.pop()
            currentScreen.value = screenStack.peek()
        }
    }
}