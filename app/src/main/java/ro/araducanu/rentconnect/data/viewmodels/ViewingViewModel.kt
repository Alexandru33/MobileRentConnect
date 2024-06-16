package ro.araducanu.rentconnect.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch
import ro.araducanu.rentconnect.data.models.Viewing
import ro.araducanu.rentconnect.data.repositories.ViewingRepositoryImpl

class ViewingViewModel : ViewModel() {

    private val viewingRepository = ViewingRepositoryImpl()
    private val _viewingDetails = MutableLiveData<Viewing>()
    val viewingDetails : LiveData<Viewing> = _viewingDetails

    private val _viewingsList = MutableLiveData<List<Viewing>>()
    val viewingsList : LiveData<List<Viewing>> = _viewingsList

    init {
        _viewingDetails.value = Viewing()
    }

    fun updateDate(value: Timestamp) {
        _viewingDetails.value = _viewingDetails.value?.copy(date = value)
    }
    fun updateTime(value: String) {
        _viewingDetails.value = _viewingDetails.value?.copy(time = value)
    }
    fun updateEmailLandlord(value: String) {
        _viewingDetails.value = _viewingDetails.value?.copy(emailLandlord = value)
    }
    fun updateEmailTenant(value: String) {
        _viewingDetails.value = _viewingDetails.value?.copy(emailTenant = value)
    }
    fun updateStatus(value: String) {
        _viewingDetails.value = _viewingDetails.value?.copy(status = value)
    }

    fun submitViewingsData() {
        viewModelScope.launch {
            viewingDetails.value?.let { viewing ->
                // Aici poți apela metoda de adăugare în Firebase sau alt serviciu
                Log.d("ViewingViewModel", "submitViewingData: $viewing")
                viewingRepository.addViewing(
                    viewing
                )
            }
        }
    }

    fun updateViewingStatus(propertyId: String, date: Timestamp, time: String, newStatus: String, emailTenant : String, onUpdate : () -> Unit) {
        viewModelScope.launch {
            viewingRepository.updateViewingStatus(propertyId, date, time, newStatus, emailTenant)
            // Optionally, refresh the list after updating
            onUpdate()
        }
    }

    fun addViewing( viewing : Viewing )
    {
        viewModelScope.launch{

            viewingRepository.addViewing(viewing)
        }
    }

    fun getViewingsOfPropertyIdWithStatus (propertyId : String, status : String )
    {
        viewModelScope.launch {
            val viewings = viewingRepository.getViewingsOfPropertyIdWithStatus(propertyId, status).toList()
            Log.d( "VIEWING VIEWMODEL", "getting viewings of this property $viewings")
            Log.d( "VIEWING VIEWMODEL", "viewingsList before  ${_viewingsList.value}")
            _viewingsList.postValue(viewings)
            Log.d( "VIEWING VIEWMODEL", "viewingsList after ${_viewingsList.value}")

        }

    }


}