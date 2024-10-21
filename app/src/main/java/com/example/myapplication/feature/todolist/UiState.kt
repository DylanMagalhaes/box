package com.example.myapplication.feature.todolist

data class UiState(
    val tasks: List<Task>,
    val inputValue: String,
) {
    companion object {
        val Default = UiState(
            tasks = emptyList(),
            inputValue = ""
        )
    }
}

data class Task(
    val description: String
)
