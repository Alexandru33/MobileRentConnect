package ro.araducanu.rentconnect.data.add

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class AddViewModel : ViewModel() {

    private val TAG = AddViewModel::class.simpleName

    var addUIState = mutableStateOf(AddUIState())

    var allValidationsPassed = mutableStateOf(false)

    var submitInProgress = mutableStateOf(false)


    fun onEvent(event: AddUIEvent) {
        when (event) {
            is AddUIEvent.TitleChanged -> {
                addUIState.value = addUIState.value.copy(
                    title = event.title
                )
            }

            is AddUIEvent.AddressChanged -> {
                addUIState.value = addUIState.value.copy(
                    location = event.address
                )
            }
            is AddUIEvent.DescriptionChanged -> {
                addUIState.value = addUIState.value.copy(
                    description = event.description
                )
            }
            is AddUIEvent.FloorChanged -> {
                addUIState.value = addUIState.value.copy(
                    floor = event.floor
                )
            }
            is AddUIEvent.PriceChanged -> {
                addUIState.value = addUIState.value.copy(
                    price = event.price
                )
            }
            is AddUIEvent.RoomsChanged -> {
                addUIState.value = addUIState.value.copy(
                    rooms = event.rooms
                )
            }
            AddUIEvent.SubmitButtonClicked -> {
                    submitProperty()

            }
            is AddUIEvent.TypeChanged -> {
                addUIState.value = addUIState.value.copy(
                    type = event.type
                )
            }
            is AddUIEvent.YearOfConstructionChanged -> {
                addUIState.value = addUIState.value.copy(
                    year = event.yearOfConstruction
                )
            }

            is AddUIEvent.SizeChanged -> {
                addUIState.value = addUIState.value.copy(
                    size = event.size
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

    private fun submitProperty() {

        submitInProgress.value = true

        viewModelScope.launch {

            try {

                val title = addUIState.value.title
                val description = addUIState.value.description
                val address = addUIState.value.location
                val type = addUIState.value.type
                val floor = addUIState.value.floor
                val year = addUIState.value.year
                val rooms = addUIState.value.rooms
                val price = addUIState.value.price
                val phone = addUIState.value.phone
                val email = addUIState.value.email
                val size = addUIState.value.size

                val propertyHashMap = hashMapOf(
                    "title" to title,
                    "description" to description,
                    "location" to address,
                    "type" to type,
                    "floor" to floor,
                    "year" to year,
                    "rooms" to rooms,
                    "price" to price,
                    "phone" to phone,
                    "email" to email,
                    "size" to size
                )

                val db = Firebase.firestore
                val documentReference = db
                    .collection("properties")
                    .add(propertyHashMap)
                    .await()
//                    .addOnSuccessListener {
//                        Log.d(TAG,"Inside_add_success")
//                        Log.d(TAG,"${it}")
//
//                        submitInProgress.value = false
//                        RentConnectAppRouter.navigateTo(Screen.SearchScreen)
//                    }
//                    .addOnFailureListener{
//                        Log.d(TAG,"Inside_add_failure")
//                        Log.d(TAG,"${it}")
//
//                        submitInProgress.value = false
//                    }


                db
                    .collection("properties")
                    .document(documentReference.id)
                    .update( mapOf( "id" to documentReference.id ))
                    .await()

                addUIState.value.id = documentReference.id
            }
            catch (e: Exception) {
                Log.d(TAG, "Inside_add_failure")
                Log.d(TAG, "$e")

                submitInProgress.value = false
            }



        }



    }

}


