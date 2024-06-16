package ro.araducanu.rentconnect.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ro.araducanu.rentconnect.data.models.PropertyLong
import ro.araducanu.rentconnect.data.models.Viewing

interface ViewingRepository {
}

class ViewingRepositoryImpl : ViewingRepository {

    private val db  = FirebaseFirestore.getInstance()

    suspend fun addViewing(
        viewing : Viewing

    ){
        val viewingData = viewing.toMap()

        try {
            db.collection("viewings")
                .add(viewingData)
                .await() // Suspend function to wait for the task to complete
            println("Viewing uploaded successfully")
        } catch (e: Exception) {
            println("Error uploading viewing: ${e.message}")
        }
    }

    suspend fun updateViewingStatus(propertyId: String, date: Timestamp, time: String, newStatus: String, emailTenant : String) {
        try {
            val viewingRef = db.collection("viewings")
                .whereEqualTo("idProperty", propertyId)
                .whereEqualTo("date", date)
                .whereEqualTo("time", time)
                .get()
                .await()
                .documents
                .firstOrNull()?.reference

            viewingRef
                ?.update("status", newStatus)
                ?.await()

            viewingRef
                ?.update("emailTenant", emailTenant)
                ?.await()

            Log.d("ViewingRepo", "Status updated successfully and added email Tenant")
        } catch (e: Exception) {
            Log.e("ViewingRepo", "Error updating status: ${e.message}")
        }
    }


    fun getViewingsOfEmail(email : String?): Flow<List<Viewing>> = callbackFlow {
        val listener = db.collection("viewings")
            .whereEqualTo("emailLandlord", email)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }

                val viewingsList = snapshot?.documents?.mapNotNull {
                    it.toObject(Viewing::class.java)
                } ?: listOf()

                trySend(viewingsList)
            }

        awaitClose { listener.remove() }
    }

//    fun getViewingsOfPropertyId(propertyId : String): Flow<List<Viewing>> = callbackFlow {
//        val listener = db.collection("viewings")
//            .whereEqualTo("idProperty", propertyId)
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    close(e)
//                    return@addSnapshotListener
//                }
//
//                val viewingsList = snapshot?.documents?.mapNotNull {
//                    it.toObject(Viewing::class.java)
//                } ?: listOf()
//
//                trySend(viewingsList)
//            }
//
//        awaitClose { listener.remove() }
//    }

    suspend fun getViewingsOfPropertyIdWithStatus (  propertyId : String, status : String ) :List<Viewing>
    {

        return try {
            val snapshot = db.collection("viewings")
                .whereEqualTo("idProperty", propertyId)
                .whereEqualTo("status", status)
                .get()
                .await()
            Log.d("Viewing Repo", "fetch viewing for a property")

            snapshot.documents.mapNotNull { doc -> doc.toObject(Viewing::class.java) }

        }
        catch( e: Exception) {
            Log.e("ViewingRepo", e.stackTraceToString())
            emptyList()
        }

    }
}