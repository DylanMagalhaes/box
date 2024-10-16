package com.example.myapplication.feature.first

import androidx.compose.ui.graphics.Color

data class UiState(
    val boxes: List<Box>,
) {
    companion object {
        val Default = UiState(
            boxes = emptyList()
        )
    }
}

data class Box(
    val color: Color
)
