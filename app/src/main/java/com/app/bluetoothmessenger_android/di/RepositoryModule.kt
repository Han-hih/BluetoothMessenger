package com.app.bluetoothmessenger_android.di

import com.app.bluetoothmessenger_android.data.repository.FakeBluetoothMessengerRepository
import com.app.bluetoothmessenger_android.domain.repository.BluetoothMessengerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindBluetoothMessengerRepository(
        fakeBluetoothMessengerRepository: FakeBluetoothMessengerRepository
    ): BluetoothMessengerRepository
}
