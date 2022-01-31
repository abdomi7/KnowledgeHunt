package com.example.knowledgehunt.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.knowledgehunt.domain.repository.IFirebaseFirestore
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class FirebaseFirestoreImpl : IFirebaseFirestore {


    override suspend fun getUserName(): MutableLiveData<String> {

        return MutableLiveData<String>().apply {

            val db = FirebaseFirestore.getInstance()
            FirebaseAuth.getInstance().uid.let {
                db.collection("user").document(
                    it!!
                )
            }.addSnapshotListener { value, e ->

                setValue(value?.get("name").toString())

            }
        }
    }


    override suspend fun addUserDataToFirestore(data: MutableMap<String, Any?>): Task<Void> {

        return FirebaseFirestore.getInstance().collection("user")
            .document(FirebaseAuth.getInstance().uid.toString())
            .set(data)
    }

    override suspend fun addArticleDataToFirestore(data: MutableMap<String, Any?>): Task<DocumentReference> {

        return FirebaseFirestore.getInstance().collection("articles")
            .add(data)
    }

    override suspend fun getCollectionFromFirestore(collectionPath: String): CollectionReference {

        return FirebaseFirestore.getInstance().collection(collectionPath)


    }

    override suspend fun addDataToDocument(
        collection: String,
        id: String,
        data: MutableMap<String, Any?>
    ): Task<Void> {
        return FirebaseFirestore.getInstance().collection(collection).document(id)
            .set(data, SetOptions.merge())
    }

    override suspend fun getDocumentById(collection: String, id: String): Task<DocumentSnapshot> {
        return FirebaseFirestore.getInstance().collection(collection).document(id).get()
    }


}