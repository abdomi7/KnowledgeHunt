package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseFirestore
import com.google.firebase.Timestamp

class GetMyArticleId(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collection: String,
        idValue: String,
        idFiledName: String,
        dateFieldName: String,
        DateFieldValue: Timestamp?
    ) =
        repository.getDocumentByStringFieldAndTimestampField(
            collection,
            idValue,
            idFiledName,
            dateFieldName,
            DateFieldValue
        )
}