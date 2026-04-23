package com.app.bluetoothmessenger_android.domain.model

data class ConnectedPerson(
    val name: String,
    val initials: String,
    val isConnected: Boolean,
    val profileTone: ProfileTone
)
