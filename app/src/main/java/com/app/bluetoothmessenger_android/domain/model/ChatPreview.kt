package com.app.bluetoothmessenger_android.domain.model

data class ChatPreview(
    val name: String,
    val initials: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int,
    val profileTone: ProfileTone
)
