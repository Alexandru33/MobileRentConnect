package ro.araducanu.rentconnect.data.repositories

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import ro.araducanu.rentconnect.data.models.PropertyImage

class PropertyImageRepository {

    private val db = Firebase.storage.reference


    suspend fun fetchImageUrlsByPropertyId ( propertyID : String  ) : List <String> = withContext(Dispatchers.IO) {
        try {
            val listResult = db.child("$propertyID/").listAll().await()
            listResult.items.map { it.downloadUrl.await().toString() }
        } catch (e: Exception) {
            emptyList()  // Handle exceptions by returning an empty list
        }
    }

    suspend fun fetchMainImageByPropertyId(propertyID: String): String {

        Log.d("AirplaneImageRepository", "fetchMainImageByAirplaneModel: $propertyID")
        return db.child("$propertyID/main.png").downloadUrl.await().toString()
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun fetchAllMainImagesLiveData(propertyIDs: List<String>): LiveData<List<String>> {
        val liveData = MutableLiveData<List<String>>()

        GlobalScope.launch(Dispatchers.IO) {
            val allMainImages = mutableListOf<String>()

            for (model in propertyIDs) {
                try {
                    val mainImageRef = db.child("$model/main.png")
                    val mainImageUrl = mainImageRef.downloadUrl.await().toString()
                    allMainImages.add(mainImageUrl)
                } catch (e: Exception) {
                    // Skip to the next model if any error occurs
                    continue
                }
            }

            withContext(Dispatchers.Main) {
                liveData.value = allMainImages
            }
        }

        return liveData
    }



    suspend fun uploadImage(imageUri: Uri, propertyID: String, identifier: String): String {
        val imagePath = "$propertyID/$identifier.png"
        val imageRef = db.child(imagePath)
        val uploadTask = imageRef.putFile(imageUri).await()
        return uploadTask.storage.downloadUrl.await().toString()
    }

    suspend fun uploadNewPlaneImages(propertyImage: PropertyImage, propertyID: String): Result<List<String>> = withContext(Dispatchers.IO){
        try {
            val imageUrls = mutableListOf<String>()
            val mainImageUrl = uploadImage(propertyImage.mainImage, propertyID, "main")
            imageUrls.add(mainImageUrl)
            var imageIndex = 0
            propertyImage.imageList.forEachIndexed { index, uri ->
                val url = uploadImage(uri, propertyID, imageIndex.toString())
                imageIndex++
                imageUrls.add(url)
            }
            Result.success(imageUrls)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }





}