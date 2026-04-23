package com.app.bluetoothmessenger_android.domain.model

data class NearbyDevice(
    val name: String,
    val description: String,
    val isActive: Boolean,
    val profileTone: ProfileTone
)
