package com.app.bluetoothmessenger_android.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.app.bluetoothmessenger_android.ui.discovery.DeviceDiscoveryRoute
import com.app.bluetoothmessenger_android.ui.home.HomeRoute

@Composable
fun BluetoothMessengerApp() {
    var currentScreen by rememberSaveable { mutableStateOf(AppDestination.Home) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (currentScreen) {
            AppDestination.Home -> HomeRoute(
                modifier = Modifier.padding(innerPadding),
                onBluetoothClick = { currentScreen = AppDestination.DeviceDiscovery }
            )
            AppDestination.DeviceDiscovery -> DeviceDiscoveryRoute(
                modifier = Modifier.padding(innerPadding),
                onBackClick = { currentScreen = AppDestination.Home }
            )
        }
    }
}

private enum class AppDestination {
    Home,
    DeviceDiscovery
}
