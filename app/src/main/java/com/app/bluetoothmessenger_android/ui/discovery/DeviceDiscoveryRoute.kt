package com.app.bluetoothmessenger_android.ui.discovery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bluetoothmessenger_android.presentation.discovery.DeviceDiscoveryViewModel

@Composable
fun DeviceDiscoveryRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: DeviceDiscoveryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DeviceDiscoveryScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick
    )
}
