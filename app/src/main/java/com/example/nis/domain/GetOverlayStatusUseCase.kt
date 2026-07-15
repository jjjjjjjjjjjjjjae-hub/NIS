package com.example.nis.domain

import javax.inject.Inject

class GetOverlayStatusUseCase @Inject constructor(
    private val repository: OverlayRepository
) {

    /**
     * Overlay іске қосуға бола ма, соны тексереді.
     */
    operator fun invoke(): Boolean {
        return repository.isOverlayEnabledByAdmin()
    }
}
