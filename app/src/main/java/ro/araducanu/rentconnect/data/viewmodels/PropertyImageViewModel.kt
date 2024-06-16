package ro.araducanu.rentconnect.data.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import ro.araducanu.rentconnect.data.models.PropertyImage
import ro.araducanu.rentconnect.data.repositories.PropertyImageRepository

class PropertyImageViewModel() : ViewModel() {

    private val repository : PropertyImageRepository = PropertyImageRepository()

    private val propertyViewModel = PropertyViewModel()
    private val planeModels = propertyViewModel.propertyIDs
    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> = _images
    private val _mainImages = MutableLiveData<Map<String, String>>()
    val mainImages: LiveData<Map<String, String>> = _mainImages
    private val _allImages = MutableLiveData<Map<String, List<String>>>()
    val allImages: LiveData<Map<String, List<String>>> = _allImages

    private val _uploadStatus = MutableLiveData<String>()
    val uploadStatus: LiveData<String> = _uploadStatus
    private val _imageList = mutableStateListOf<Uri>()
    val imageList: List<Uri> get() = _imageList


    init {
        fetchPropertyMainImages()
        fetchAllPropertyImages()
    }

    fun fetchImageUrls(propertyID: String) {
        viewModelScope.launch {
            Log.d("PropertyImageViewModel", "fetchImageUrls: $propertyID")
            val imageUrls = repository.fetchImageUrlsByPropertyId(propertyID)
            Log.d("PropertyImageViewModel", "fetchImageUrls: $imageUrls")

            _images.postValue(imageUrls)
        }
    }

    private fun fetchPropertyMainImages(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val db = FirebaseFirestore.getInstance()
                val storage = FirebaseStorage.getInstance()
                val documents = db.collection("properties").get().await()
                val imageMap = mutableMapOf<String, String>()

                documents.documents.forEach { document ->
                    val propertyID = document.getString("id")
                    if(propertyID != null){
                        val imagePath = "$propertyID/main.png"
                        val imageRef = storage.getReference(imagePath)
                        val imageUrl = imageRef.downloadUrl.await().toString()
                        Log.d("PropertyImageViewModel", "fetchPropertyMainImages: $imageUrl")
                        imageMap[propertyID] = imageUrl
                        Log.d("PropertyImageViewModel", "fetchPropertyMainImages: ${imageMap[propertyID]}")
                    }

                }

                _mainImages.postValue(imageMap)
            }
            catch (e: Exception){
                Log.d("PropertyImageViewModel", "fetchPropertyMainImages: ${e.message}")
                // Handle exception, such as posting an error message or logging
            }
        }
    }

    fun fetchAllPropertyImages(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val db = FirebaseFirestore.getInstance()
                val storage = FirebaseStorage.getInstance()
                val documents = db.collection("properties").get().await()
                val imageMap = mutableMapOf<String, List<String>>()

                documents.documents.forEach { document ->
                    val propertyID = document.getString("id")
                    if(propertyID != null){
                        val imagePath = "$propertyID/"
                        val imageRef = storage.getReference(imagePath)
                        val imageUrls = imageRef.listAll().await()
                        val urls = imageUrls.items.map { it.downloadUrl.await().toString() }
                        imageMap[propertyID] = urls
                    }

                }

                _allImages.postValue(imageMap)
            }
            catch (e: Exception){
                // Handle exception, such as posting an error message or logging
            }
        }
    }


    fun uploadImagesForNewModel(propertyID: String) {
        viewModelScope.launch {
            val result = repository.uploadNewPlaneImages(PropertyImage(_imageList.first(), _imageList.drop(1)), propertyID)
            if (result.isSuccess) {
                Log.d("Upload", "Imaginile au fost încărcate cu succes la folderul $propertyID.")
                //fetchPlaneMainImages() // Reîmprospătează imaginile principale
                _imageList.clear() // O curățăm pe lista de imagini după încărcare
            } else {
                Log.e("Upload", "Eroare la încărcarea imaginilor: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    fun addImage(uri: Uri) {
        _imageList.add(uri)
    }

}