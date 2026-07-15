package com.example.nis.domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class GetOverlayStatusUseCaseTest {

    private val repository = mockk<OverlayRepository>()
    private val useCase = GetOverlayStatusUseCase(repository)

    @Test
    fun `invoke returns true when repository returns true`() {
        // Arrange
        every { repository.isOverlayEnabledByAdmin() } returns true

        // Act
        val result = useCase()

        // Assert
        assertEquals(true, result)
        verify(exactly = 1) { repository.isOverlayEnabledByAdmin() }
    }

    @Test
    fun `invoke returns false when repository returns false`() {
        // Arrange
        every { repository.isOverlayEnabledByAdmin() } returns false

        // Act
        val result = useCase()

        // Assert
        assertEquals(false, result)
        verify(exactly = 1) { repository.isOverlayEnabledByAdmin() }
    }
}
