import androidx.lifecycle.ViewModel
import com.example.myapplication.feature.todolist.Task
import com.example.myapplication.feature.todolist.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.Default)
    val uiState = _uiState.asStateFlow()

    fun onTaskInputChange(input: String) {
        _uiState.update { it.copy(inputValue = input) }
    }

    fun onAddTaskClick() {
        val task = Task(description = _uiState.value.inputValue)

        _uiState.update {
            it.copy(
                tasks = it.tasks + task
            )
        }
    }
}
