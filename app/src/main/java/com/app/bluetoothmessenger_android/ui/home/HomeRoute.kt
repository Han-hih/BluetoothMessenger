package com.app.bluetoothmessenger_android.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bluetoothmessenger_android.presentation.home.HomeViewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onBluetoothClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onBluetoothClick = onBluetoothClick
    )
}
