package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

data class AuthUseCases(
    val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    val logout: Logout,
    val getCurrentUser: GetCurrentUser,
    val sendEmailVerification: SendEmailVerification,
    val getCurrentUserID: GetCurrentUserID,
    val login: Login,
    val resetPassword: ResetPassword,
    val getCurrentUserEmail: GetCurrentUserEmail,
    val updateCurrentUserEmail: UpdateCurrentUserEmail,
    val reAuthenticateCurrentUser: ReAuthenticateCurrentUser,
)

data class FirestoreUseCases(
    val addUserDataToFirestore: AddUserDataToFirestore,
    val addArticleDataToFirestore: AddArticleDataToFirestore,
    val getArticles: GetArticles,
    val addImageUrlToArticleDocument: AddImageUrlToArticleDocument,
    val getArticleAuthorNameById: GetArticleAuthorNameById,
    val getArticleViewsCount: GetArticleViewsCount,
    val updateArticleViewsCount: UpdateArticleViewsCount,
    val getCurrentUserData: GetCurrentUserData,
    val getMyArticles: GetMyArticles,
    val getMyArticleId: GetMyArticleId,
    val deleteArticleFirestoreDocument: DeleteArticleFirestoreDocument,
    val updateProfileData: UpdateProfileData,
    val getUserDataStatistics: GetUserDataStatistics,
    val updateUserDataStatistics: UpdateUserDataStatistics,
    val getQuestions: GetQuestions,
    val addQuestion: AddQuestion,
    val getMyQuestions: GetMyQuestions,
    val deleteQuestionFirestoreDocument: DeleteQuestionFirestoreDocument,
    val updateQuestionData: UpdateQuestionData,
    val getUserDataById: GetUserDataById,
    val getMCQTests: GetMCQTests,
)

data class StorageUseCases(
    val uploadStorageImage: UploadStorageImage,
    val getStorageImageUrl: GetStorageImageUrl,
    val deleteArticleStorageImage: DeleteArticleStorageImage,
)

data class ImageUseCases(
    val compressImage: CompressImage,
)