package com.example.nis.domain
interface OverlayRepository {
    fun isOverlayEnabledByAdmin(): Boolean
    fun saveOverlayState(isEnabled: Boolean)
}
