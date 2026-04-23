package com.app.bluetoothmessenger_android.data.repository

import com.app.bluetoothmessenger_android.domain.model.ChatPreview
import com.app.bluetoothmessenger_android.domain.model.ConnectedPerson
import com.app.bluetoothmessenger_android.domain.model.NearbyDevice
import com.app.bluetoothmessenger_android.domain.model.ProfileTone
import com.app.bluetoothmessenger_android.domain.repository.BluetoothMessengerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeBluetoothMessengerRepository @Inject constructor() : BluetoothMessengerRepository {
    override fun getConnectedPeople(): List<ConnectedPerson> = listOf(
        ConnectedPerson("Phillip", "PH", true, ProfileTone.ORANGE),
        ConnectedPerson("Alfredo", "AL", true, ProfileTone.SLATE),
        ConnectedPerson("Jaylon", "JA", true, ProfileTone.CORAL),
        ConnectedPerson("Tatiana", "TA", false, ProfileTone.SAND),
        ConnectedPerson("Terry", "TE", true, ProfileTone.BROWN)
    )

    override fun getChatPreviews(): List<ChatPreview> = listOf(
        ChatPreview("Phillip Franci", "PF", "방금 받은 파일도 확인해줘.", "10:00 am", 0, ProfileTone.ORANGE),
        ChatPreview("Alfredo Saris", "AS", "좋아, 연결 상태 먼저 볼게.", "08:00 am", 1, ProfileTone.SLATE),
        ChatPreview("Jaylon Franci", "JF", "Everything's good.", "08:30 am", 0, ProfileTone.CORAL),
        ChatPreview("Tatiana Dorwart", "TD", "근처 기기가 하나 더 잡혔어.", "06:10 am", 2, ProfileTone.SAND),
        ChatPreview("Terry Bergson", "TB", "메시지 송수신 테스트해보자.", "05:45 am", 0, ProfileTone.BROWN)
    )

    override fun getNearbyDevices(): List<NearbyDevice> = listOf(
        NearbyDevice("Galaxy S24", "연결 가능 · 1m", true, ProfileTone.BLUE),
        NearbyDevice("Pixel Buds Pro", "페어링됨 · 오디오", false, ProfileTone.PURPLE),
        NearbyDevice("Inho's Tablet", "새 기기 · 3m", false, ProfileTone.GREEN),
        NearbyDevice("Office Speaker", "검색됨 · 5m", false, ProfileTone.YELLOW)
    )
}
