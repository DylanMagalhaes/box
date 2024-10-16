package com.example.myapplication.feature.first

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    viewModel: FirstScreenViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    FirstScreen(
        modifier = modifier,
        state = state,
        onDecrementClick = viewModel::onDecrementClicked,
        onIncrementClick = viewModel::onIncrementClicked,
        onBoxCountInputChange = viewModel::onBoxCountInputChanged,
        onBoxClick = viewModel::onBoxClick,
    )
}

@Composable
private fun FirstScreen(
    state: UiState,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    onBoxCountInputChange: (String) -> Unit,
    onBoxClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .background(Color.Cyan)
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {

            state.boxes.forEachIndexed { index, box ->
                RoundedBox(
                    onClick = { onBoxClick(index) },
                    color = box.color
                )
            }
        }

        BottomBar(
            modifier = Modifier.fillMaxWidth(),
            boxCount = state.boxes.size,
            onDecrementClick = onDecrementClick,
            onIncrementClick = onIncrementClick,
            onBoxCountInputChange = onBoxCountInputChange,
        )
    }

}

@Composable
private fun RoundedBox(
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color)
            .size(150.dp)
            .clickable(onClick = onClick)
    )
}

@Composable
private fun BottomBar(
    boxCount: Int,
    modifier: Modifier = Modifier,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    onBoxCountInputChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .background(Color.Gray)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Button(
            onClick = onDecrementClick
        ) {
            Text(text = "-")
        }

        TextField(
            value = "$boxCount",
            onValueChange = onBoxCountInputChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Button(
            onClick = onIncrementClick
        ) {
            Text(text = "+")
        }
    }
}


@Preview
@Composable
private fun Preview_FirstScreen() {
    MaterialTheme {
        FirstScreen(
            modifier = Modifier.fillMaxSize(),
            state = UiState(
                boxes = listOf(
                    Box(
                        color = Color.Yellow,
                    )
                )
            ),
            onDecrementClick = {},
            onIncrementClick = {},
            onBoxCountInputChange = {},
            onBoxClick = {}
        )
    }
}