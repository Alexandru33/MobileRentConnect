package ro.araducanu.rentconnect.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import ro.araducanu.rentconnect.data.models.PropertyLong

interface PropertyRepository {

    suspend fun updateProperty(property: PropertyLong, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)
    suspend fun deleteProperty(property:PropertyLong, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)
}

class PropertyRepositoryImpl : PropertyRepository {




    private val db = FirebaseFirestore.getInstance()
    override suspend fun updateProperty(
        property: PropertyLong,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            db.collection("properties").document(property.id!!).set(property).await()
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    override suspend fun deleteProperty(
        property: PropertyLong,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    suspend fun getAllPropertyIDs(): List<String> =  withContext(Dispatchers.IO) {
        try {
            val snapshot = db.collection("properties").get().await()
            snapshot.documents.mapNotNull { it.getString("id") }
        } catch (e: Exception) {
            emptyList<String>()
        }
    }

    suspend fun getAllPropertyIDsExceptEmail(email: String): List<String> = withContext(Dispatchers.IO) {
        try {
            val snapshot = db.collection("properties")
                .whereNotEqualTo("email", email)
                .get().await()
            snapshot.documents.mapNotNull { it.getString("id") }
        } catch (e: Exception) {
            emptyList<String>()
        }
    }

    suspend fun getAllPropertyIDsOnlyForThisEmail(email: String): List<String> = withContext(Dispatchers.IO) {
        try {
            val snapshot = db.collection("properties")
                .whereEqualTo("email", email)
                .get().await()
            snapshot.documents.mapNotNull { it.getString("id") }
        } catch (e: Exception) {
            emptyList<String>()
        }
    }

    fun getPropertiesOfEmail(email : String?): Flow<List<PropertyLong>> = callbackFlow {
        val listener = db.collection("properties")
            .whereEqualTo("email", email)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }

                val propertiesList = snapshot?.documents?.mapNotNull {
                    it.toObject(PropertyLong::class.java)
                } ?: listOf()

                trySend(propertiesList)
            }

        awaitClose { listener.remove() }
    }

    fun getPropertiesExceptEmail(email : String?): Flow<List<PropertyLong>> = callbackFlow {
        val listener = db.collection("properties")
            .whereNotEqualTo("email", email)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }

                val propertiesList = snapshot?.documents?.mapNotNull {
                    it.toObject(PropertyLong::class.java)
                } ?: listOf()

                trySend(propertiesList)
            }

        awaitClose { listener.remove() }
    }



    fun getProperties(): Flow<List<PropertyLong>> = callbackFlow {
        val listener = db.collection("properties")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }

                val propertiesList = snapshot?.documents?.mapNotNull {
                    it.toObject(PropertyLong::class.java)
                } ?: listOf()

                trySend(propertiesList)
            }

        awaitClose { listener.remove() }
    }


}