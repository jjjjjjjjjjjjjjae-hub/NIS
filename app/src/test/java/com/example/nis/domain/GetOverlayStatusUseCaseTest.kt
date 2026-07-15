package com.example.nis.domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetOverlayStatusUseCaseTest {

    private lateinit var repository: OverlayRepository
    private lateinit var useCase: GetOverlayStatusUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetOverlayStatusUseCase(repository)
    }

    @Test
    fun `invoke returns true when repository returns true`() {
        // Arrange
        every { repository.isOverlayEnabledByAdmin() } returns true

        // Act
        val result = useCase()

        // Assert
        assertTrue(result)
        verify(exactly = 1) {
            repository.isOverlayEnabledByAdmin()
        }
    }

    @Test
    fun `invoke returns false when repository returns false`() {
        // Arrange
        every { repository.isOverlayEnabledByAdmin() } returns false

        // Act
        val result = useCase()

        // Assert
        assertFalse(result)
        verify(exactly = 1) {
            repository.isOverlayEnabledByAdmin()
        }
    }

    @Test
    fun `invoke calls repository only once`() {
        // Arrange
        every { repository.isOverlayEnabledByAdmin() } returns true

        // Act
        useCase()

        // Assert
        verify(exactly = 1) {
            repository.isOverlayEnabledByAdmin()
        }
    }
}
