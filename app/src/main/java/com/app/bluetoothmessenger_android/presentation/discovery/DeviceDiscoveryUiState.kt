package com.app.bluetoothmessenger_android.presentation.discovery

import com.app.bluetoothmessenger_android.domain.model.NearbyDevice

data class DeviceDiscoveryUiState(
    val nearbyDevices: List<NearbyDevice> = emptyList()
)
