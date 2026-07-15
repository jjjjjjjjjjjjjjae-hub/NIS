package com.example.nis.domain

interface OverlayRepository {

    /**
     * Overlay-дың әкімші тарапынан рұқсат етілгенін тексереді.
     */
    fun isOverlayEnabledByAdmin(): Boolean

    /**
     * Overlay күйін сақтайды.
     */
    fun saveOverlayState(isEnabled: Boolean)

    /**
     * Overlay күйін ауыстырады.
     */
    fun toggleOverlayState() {
        saveOverlayState(!isOverlayEnabledByAdmin())
    }
}
