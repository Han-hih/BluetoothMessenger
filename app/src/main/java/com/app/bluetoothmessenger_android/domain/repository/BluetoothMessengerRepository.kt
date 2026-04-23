package com.app.bluetoothmessenger_android.domain.repository

import com.app.bluetoothmessenger_android.domain.model.ChatPreview
import com.app.bluetoothmessenger_android.domain.model.ConnectedPerson
import com.app.bluetoothmessenger_android.domain.model.NearbyDevice

interface BluetoothMessengerRepository {
    fun getConnectedPeople(): List<ConnectedPerson>
    fun getChatPreviews(): List<ChatPreview>
    fun getNearbyDevices(): List<NearbyDevice>
}
