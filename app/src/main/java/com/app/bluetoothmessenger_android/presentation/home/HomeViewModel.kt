package com.app.bluetoothmessenger_android.presentation.home

import androidx.lifecycle.ViewModel
import com.app.bluetoothmessenger_android.domain.usecase.GetChatPreviewsUseCase
import com.app.bluetoothmessenger_android.domain.usecase.GetConnectedPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    getConnectedPeopleUseCase: GetConnectedPeopleUseCase,
    getChatPreviewsUseCase: GetChatPreviewsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            connectedPeople = getConnectedPeopleUseCase(),
            chats = getChatPreviewsUseCase()
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
