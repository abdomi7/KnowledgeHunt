package com.abdelHalimMahmoud.knowledgehunt.domain.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class QuestionItemData(
    val views: Long? = null,
    val user_id: String? = null,
    val title: String? = null,
    var question_upvotes: Long? = null,
    var question_downvotes: Long? = null,
    val prog_language: String? = null,
    val field: String? = null,
    @ServerTimestamp
    val date: Timestamp? = null,
    val content: String? = null,
    var answers: MutableList<HashMap<String, Any>?>? = null,
)