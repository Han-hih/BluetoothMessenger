package com.app.bluetoothmessenger_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.bluetoothmessenger_android.ui.BluetoothMessengerApp
import com.app.bluetoothmessenger_android.ui.theme.BluetoothMessenger_AndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BluetoothMessenger_AndroidTheme {
                BluetoothMessengerApp()
            }
        }
    }
}
