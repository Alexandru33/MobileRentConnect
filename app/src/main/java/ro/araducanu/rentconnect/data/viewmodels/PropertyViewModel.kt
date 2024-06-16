package ro.araducanu.rentconnect.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import ro.araducanu.rentconnect.data.models.PropertyLong
import ro.araducanu.rentconnect.data.repositories.PropertyRepository
import ro.araducanu.rentconnect.data.repositories.PropertyRepositoryImpl

class PropertyViewModel : ViewModel()  {

    private val propertyRepository = PropertyRepositoryImpl()

    private val emailUser = FirebaseAuth.getInstance().currentUser?.email

    private val _propertyDetails = MutableLiveData<PropertyLong>()

    val propertyDetails: LiveData<PropertyLong> = _propertyDetails
    private val _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean> = _showToast
    private val _filteredProperties = MutableLiveData<List<PropertyLong>>()
    val filteredProperties: LiveData<List<PropertyLong>> = _filteredProperties

    private val _propertiesOfUser = MutableLiveData<List<PropertyLong>>()
    val propertiesOfUser : LiveData<List<PropertyLong>> = _propertiesOfUser
    val propertiesOfUser2 = propertyRepository.getPropertiesOfEmail(emailUser).asLiveData()


    private val _propertiesExceptUser = MutableLiveData<List<PropertyLong>>()
    val propertiesExceptUser : LiveData<List<PropertyLong>> = _propertiesExceptUser




    val propertiess = propertyRepository.getProperties().asLiveData()
    //val propertiesExceptUser = propertyRepository.getPropertiesExceptEmail(emailUser).asLiveData()





    fun fetchPropertiesOfUser(email : String) {
        viewModelScope.launch {
            Log.d("PropertyViewModel" , "getpropertiesofUser : $emailUser")

            val propertiesOfUserLocal = propertyRepository.getPropertiesOfEmail(emailUser).asLiveData()

            _propertiesOfUser.postValue(propertiesOfUserLocal.value)
        }

    }

    fun fetchPropertiesExceptUser(email : String) {
        viewModelScope.launch {
            Log.d("PropertyViewModel" , "getpropertiesExceptUser : $emailUser")

            val propertiesLocal = propertyRepository.getPropertiesExceptEmail(emailUser).asLiveData()

            _propertiesExceptUser.postValue(propertiesLocal.value)
        }
    }


    init {
        _propertyDetails.value = PropertyLong()
    }

    // Utilizăm un StateFlow pentru a reține query-ul de căutare
    private val searchQuery = MutableStateFlow("")

    // Planes care reacționează la schimbările în searchQuery
    val propertiesList = searchQuery.flatMapLatest { query ->
        if (query.isEmpty()) {
            propertyRepository.getProperties().map { list ->
                list.filter { !it.email.equals(emailUser) }
            }  // Returnează toate properties-urile dacă query-ul este gol
        } else {
            propertyRepository.getProperties().map { list ->
                list.filter { it.title.contains(query, ignoreCase = true) &&  !it.email.equals(emailUser) }
            }
        }
    }.asLiveData()

    // Actualizează query-ul de căutare și trigger-ează un nou flux de date
    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }


    fun resetToastState() {
        _showToast.value = false
    }




    val propertyIDs = liveData(Dispatchers.IO) {
        val models = propertyRepository.getAllPropertyIDs()
        emit(models)
    }

    // LiveData to hold the selected plane
    private val _selectedProperty = MutableLiveData<PropertyLong?>()
    val selectedProperty: LiveData<PropertyLong?> = _selectedProperty

    // Method to set the selected plane
    fun selectProperty(property: PropertyLong) {
        _selectedProperty.value = property
    }

    // Method to clear the selection when needed
    fun clearSelectedPlane() {
        _selectedProperty.value = null
    }



    fun updateTitle(value: String) {
        _propertyDetails.value = _propertyDetails.value?.copy(title = value)
    }
    fun updateDescription(value: String) {
        _propertyDetails.value = _propertyDetails.value?.copy(description = value)
    }
    fun updatePrice(value: String) {
        _propertyDetails.value = _propertyDetails.value?.copy(price = value)
    }
    fun updateRooms(value: Int) {
        _propertyDetails.value = _propertyDetails.value?.copy(rooms = value)
    }

    fun updateProperty(){
        viewModelScope.launch {


            propertyDetails.value?.id = selectedProperty.value?.id
            propertyDetails.value?.yearOfConstruction = selectedProperty.value!!.yearOfConstruction
            propertyDetails.value?.email = selectedProperty.value!!.email
            propertyDetails.value?.phone = selectedProperty.value!!.phone
            propertyDetails.value?.rating = selectedProperty.value!!.rating
            propertyDetails.value?.floor = selectedProperty.value!!.floor
            propertyDetails.value?.type = selectedProperty.value!!.type
            propertyDetails.value?.reviews = selectedProperty.value!!.reviews
            propertyDetails.value?.location = selectedProperty.value!!.location
            propertyDetails.value?.size = selectedProperty.value!!.size

            propertyDetails.value?.let { property ->
                // Aici poți apela metoda de actualizare în Firebase sau alt serviciu
                Log.d("PropertyViewModel", "updateProperty: $property")
                propertyRepository.updateProperty(property,
                    onSuccess = {
                        // Dacă operația a fost realizată cu succes, poți face ceva aici
                    },
                    onFailure = {
                        // Dacă a apărut o eroare, poți face ceva aici
                    }
                )
            }
            val success = true // rezultatul funcției de actualizare
            if (success) {
                _showToast.value = true  // Afișează toast
            }
        }
    }


}