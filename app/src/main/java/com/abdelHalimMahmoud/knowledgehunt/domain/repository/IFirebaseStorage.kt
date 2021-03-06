package com.abdelHalimMahmoud.knowledgehunt.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask

interface IFirebaseStorage {
    suspend fun getInstance(): FirebaseStorage

    suspend fun getReference(): StorageReference

    suspend fun uploadStorageImage(
        bitmap: Bitmap,
        bath: String,
        name: String
    ): StorageTask<UploadTask.TaskSnapshot>


    suspend fun getStorageImageUrl(
        bath: String,
        name: String
    ): Task<Uri>

    suspend fun deleteStorageImageUrl(
        bath: String,
        name: String
    ): Task<Void>

}