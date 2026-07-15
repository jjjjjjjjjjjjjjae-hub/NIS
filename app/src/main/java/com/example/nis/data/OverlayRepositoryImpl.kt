package com.example.nis.data

import com.example.nis.domain.OverlayRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OverlayRepositoryImpl @Inject constructor() : OverlayRepository {

    // Кейін DataStore немесе Room қолдануға болады
    @Volatile
    private var overlayEnabled = true

    override fun isOverlayEnabledByAdmin(): Boolean {
        return overlayEnabled
    }

    override fun saveOverlayState(isEnabled: Boolean) {
        overlayEnabled = isEnabled
    }

    override fun toggleOverlayState() {
        overlayEnabled = !overlayEnabled
    }
}
