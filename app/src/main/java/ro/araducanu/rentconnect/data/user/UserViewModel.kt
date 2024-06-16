package ro.araducanu.rentconnect.data.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen


class UserViewModel : ViewModel() {

    private val TAG = UserViewModel::class.simpleName

    var userUIState = mutableStateOf(UserUIState())

    var allValidationsPassed = mutableStateOf(false)

    var userActionInProgress = mutableStateOf(false)


    fun onEvent(event: UserUIEvent) {
        when (event) {


            is UserUIEvent.FirstNameChanged -> {
                userUIState.value = userUIState.value.copy(
                    firstName = event.firstName
                )
            }

            UserUIEvent.CreateButtonClicked -> {
                createUser()
            }
            UserUIEvent.FetchUserButtonClicked ->{
                fetchUser()
            }

            is UserUIEvent.EmailChanged -> {
                userUIState.value = userUIState.value.copy(
                    email = event.email
                )
            }
            is UserUIEvent.PhoneChanged -> {
                userUIState.value = userUIState.value.copy(
                    phone = event.phone
                )
            }

            UserUIEvent.ModifyUserButtonClicked -> {
                modifyUser()
            }

            is UserUIEvent.LastNameChanged -> {
                userUIState.value = userUIState.value.copy(
                    lastName = event.lastName
                )
            }

        }
        //validateLoginUIDataWithRules()
    }

//    private fun validateLoginUIDataWithRules() {
//        val emailResult = Validator.validateEmail(
//            email = loginUIState.value.email
//        )
//
//
//        val passwordResult = Validator.validatePassword(
//            password = loginUIState.value.password
//        )
//
//        loginUIState.value = loginUIState.value.copy(
//            emailError = emailResult.status,
//            passwordError = passwordResult.status
//        )
//
//        allValidationsPassed.value = emailResult.status && passwordResult.status
//
//    }

    private fun createUser() {

        userActionInProgress.value = true
        val firstName = userUIState.value.firstName
        val lastName = userUIState.value.lastName

        val phone = userUIState.value.phone
        val email = userUIState.value.email

        val propertyHashMap = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "phone" to phone,
        )
        val db = Firebase.firestore
        db
            .collection("users")
            .add(propertyHashMap)
            .addOnSuccessListener {
                Log.d(TAG,"Inside_add_user_success")
                Log.d(TAG,"${it}")

                    userActionInProgress.value = false
                    RentConnectAppRouter.navigateTo(Screen.SearchScreen)
            }
            .addOnFailureListener{
                Log.d(TAG,"Inside_add_failure_failure")
                Log.d(TAG,"${it}")

                userActionInProgress.value = false
            }


    }

    private fun fetchUser() {
        Log.d(TAG , "HEEEEEEEEEEEEEEEEEEEEEYYYYYYYYYYYYYYYYYYYY")

        CoroutineScope(Dispatchers.IO).launch {
            userActionInProgress.value = true
            val email = userUIState.value.email

            val db = Firebase.firestore

            val query = db
                .collection("users")
                .whereEqualTo("email",  email)
                .get()
                .await()

            if ( query.size() != 1)
            {
                Log.d(TAG , "None or Multiple users fetched!!!")
            }
            else{
                val document = query.documents[0]
                val userHashMap = document.data
                if (userHashMap == null)
                {
                    Log.d(TAG, "Null userHashMap from fetch!!!")
                }

                userUIState.value.email = userHashMap!!.get("email").toString();
                userUIState.value.lastName = userHashMap.get("lastName").toString();
                userUIState.value.firstName = userHashMap.get("firstName").toString();
                userUIState.value.phone = userHashMap.get("phone").toString();
                Log.d(TAG , userUIState.value.email
                        + " "
                        + userUIState.value.firstName
                        + " "
                        + userUIState.value.phone
                        + " "
                        + userUIState.value.lastName)
            }
        }






    }

    private fun modifyUser() {

    }

}


