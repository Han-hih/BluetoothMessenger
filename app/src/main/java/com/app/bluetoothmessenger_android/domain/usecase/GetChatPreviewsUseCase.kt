package com.app.bluetoothmessenger_android.domain.usecase

import com.app.bluetoothmessenger_android.domain.repository.BluetoothMessengerRepository
import javax.inject.Inject

class GetChatPreviewsUseCase @Inject constructor(
    private val repository: BluetoothMessengerRepository
) {
    operator fun invoke() = repository.getChatPreviews()
}
