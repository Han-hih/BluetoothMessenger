package com.app.bluetoothmessenger_android.presentation.home

import com.app.bluetoothmessenger_android.domain.model.ChatPreview
import com.app.bluetoothmessenger_android.domain.model.ConnectedPerson

data class HomeUiState(
    val connectedPeople: List<ConnectedPerson> = emptyList(),
    val chats: List<ChatPreview> = emptyList()
)
