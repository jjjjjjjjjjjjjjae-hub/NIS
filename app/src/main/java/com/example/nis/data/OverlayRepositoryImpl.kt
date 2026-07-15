package com.example.nis.data
import com.example.nis.domain.OverlayRepository
import javax.inject.Inject

class OverlayRepositoryImpl @Inject constructor() : OverlayRepository {
    private var overlayState = true // Mocked data source (мысалы: DataStore/Room)
    
    override fun isOverlayEnabledByAdmin(): Boolean = overlayState
    override fun saveOverlayState(isEnabled: Boolean) { overlayState = isEnabled }
}
