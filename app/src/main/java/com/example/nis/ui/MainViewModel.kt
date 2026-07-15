package com.example.nis.ui
import androidx.lifecycle.ViewModel
import com.example.nis.domain.GetOverlayStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOverlayStatusUseCase: GetOverlayStatusUseCase
) : ViewModel() {

    private val _canStartService = MutableStateFlow(false)
    val canStartService: StateFlow<Boolean> = _canStartService

    fun checkBusinessLogic() {
        // UI логикасын UseCase-тен алынған мәліметпен басқару
        _canStartService.value = getOverlayStatusUseCase()
    }
}
