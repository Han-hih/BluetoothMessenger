package com.app.bluetoothmessenger_android.presentation.discovery

import androidx.lifecycle.ViewModel
import com.app.bluetoothmessenger_android.domain.usecase.GetNearbyDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DeviceDiscoveryViewModel @Inject constructor(
    getNearbyDevicesUseCase: GetNearbyDevicesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        DeviceDiscoveryUiState(
            nearbyDevices = getNearbyDevicesUseCase()
        )
    )
    val uiState: StateFlow<DeviceDiscoveryUiState> = _uiState.asStateFlow()
}
