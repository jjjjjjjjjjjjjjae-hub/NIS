package com.example.nis.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nis.domain.GetOverlayStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOverlayStatusUseCase: GetOverlayStatusUseCase
) : ViewModel() {

    private val _canStartService = MutableStateFlow(false)
    val canStartService: StateFlow<Boolean> = _canStartService.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun checkBusinessLogic() {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                _canStartService.value = getOverlayStatusUseCase()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        checkBusinessLogic()
    }
}
