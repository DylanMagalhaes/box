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
            it.copy(boxes = it.boxes + Box(color = Color.Red))
        }
    }

    fun onDecrementClicked() {
        if (_uiState.value.boxes.isEmpty()) return

        _uiState.update {
            it.copy(boxes = it.boxes.toMutableList().dropLast(1))
        }
    }

    fun onBoxCountInputChanged(input: String) {
        val count = input.toIntOrNull() ?: return

        _uiState.update {
            val newBoxes = it.boxes.toMutableList()
            repeat(count) {
                newBoxes.add(Box(color = Color.Red))
            }

            it.copy(boxes = newBoxes)
        }
    }


    fun onBoxClick(index: Int) {
        _uiState.update {
            it.copy(
                boxes = it.boxes.mapIndexed { i, box ->
                    if (index == i) {
                        box.copy(color = Color.Green)
                    } else {
                        box
                    }
                }
            )
        }
    }
}

