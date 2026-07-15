package com.example.nis.di
import com.example.nis.data.OverlayRepositoryImpl
import com.example.nis.domain.OverlayRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindOverlayRepository(
        overlayRepositoryImpl: OverlayRepositoryImpl
    ): OverlayRepository
}
