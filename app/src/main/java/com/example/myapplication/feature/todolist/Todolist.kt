import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.feature.todolist.Task


@Composable
fun TodolistScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoListViewModel = viewModel(),
) {
    // Observer les valeurs du ViewModel
    val uiState by viewModel.uiState.collectAsState()

    Todolist(
        modifier = modifier,
        inputValue = uiState.inputValue,
        onTaskInputChange = { viewModel.onTaskInputChange(it) },
        onAddButtonClick = { viewModel.onAddTaskClick() },
        tasks = uiState.tasks
    )
}

@Composable
private fun Todolist(
    inputValue: String,
    onTaskInputChange: (String) -> Unit,
    onAddButtonClick: () -> Unit,
    tasks: List<Task>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddTask(
            onTaskInputChange = onTaskInputChange,
            value = inputValue,
            onAddButtonClick = onAddButtonClick
        )

        TaskList(tasks = tasks)
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(tasks.size) { index ->
            Task(taskDescription = tasks[index].description)
        }
    }
}

@Composable
fun Task(
    taskDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = taskDescription
        )
    }
}

@Composable
fun AddTask(
    onTaskInputChange: (String) -> Unit,
    value: String,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = {
                onTaskInputChange(it)
            },
            modifier = Modifier.weight(1f) // Ajuste la taille de la zone de texte
        )
        Button(
            onClick = onAddButtonClick,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Add")
        }
    }
}

@Preview
@Composable
private fun Preview_Todolist() {
    MaterialTheme {
        Todolist(
            inputValue = "",
            onTaskInputChange = {},
            onAddButtonClick = {},
            tasks = emptyList()
        )
    }
}
