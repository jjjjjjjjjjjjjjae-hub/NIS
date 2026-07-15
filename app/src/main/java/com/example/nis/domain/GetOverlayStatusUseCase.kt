package com.example.nis.domain
import javax.inject.Inject

class GetOverlayStatusUseCase @Inject constructor(
    private val repository: OverlayRepository
) {
    operator fun invoke(): Boolean {
        // Бизнес логика осында болады
        return repository.isOverlayEnabledByAdmin()
    }
}
