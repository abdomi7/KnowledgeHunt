package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.mcq.viewMCQ

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelHalimMahmoud.knowledgehunt.domain.models.MCQItemData
import com.abdelHalimMahmoud.knowledgehunt.domain.use_case.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewMCQScreenViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()
    private val _MCQState = mutableStateOf<List<MCQItemData>>(listOf())
    val MCQState: State<List<MCQItemData>> = _MCQState
    var showDialog: MutableState<Boolean> = mutableStateOf(false)

    init {
        getMCQTests()
    }

    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {
            firestoreUseCases.getMCQTests("mcq").addSnapshotListener { snapshot, e ->
                _MCQState.value =
                    snapshot?.toObjects(MCQItemData::class.java) as List<MCQItemData>
            }
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            delay(2000)
            _isRefreshing.emit(false)
        }
    }

    private fun getMCQTests() {
        viewModelScope.launch {
            firestoreUseCases.getMCQTests("mcq")
                .addSnapshotListener { snapshot, e ->
                    _MCQState.value =
                        snapshot?.toObjects(MCQItemData::class.java) as List<MCQItemData>
                }
            delay(1000)
            _isRefreshing.emit(false)
        }
    }
}