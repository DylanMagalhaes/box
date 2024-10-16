package com.example.myapplication.feature.first

import androidx.compose.ui.graphics.Color

data class UiState(
    val boxCount: Int,
    val boxColor: Color
) {
    companion object {
        val Default = UiState(
            boxCount = 0,
            boxColor = Color.Red,
        )
    }
}
