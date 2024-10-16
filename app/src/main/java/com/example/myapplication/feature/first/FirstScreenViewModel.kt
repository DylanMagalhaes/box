package com.example.myapplication.feature.first

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FirstScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.Default)
    val uiState = _uiState.asStateFlow()

    fun onIncrementClicked() {
        _uiState.update {
            it.copy(boxCount = it.boxCount + 1)
        }
    }

    fun onDecrementClicked() {
        if (_uiState.value.boxCount == 0) return

        _uiState.update {
            it.copy(boxCount = it.boxCount - 1)
        }
    }

    fun onBoxCountInputChanged(input: String) {
        _uiState.update {
            it.copy(boxCount = input.toIntOrNull() ?: return)
        }
    }

    fun onBoxClick() {
        _uiState.update {
            it.copy(boxColor = Color.Green)
        }
    }
}
