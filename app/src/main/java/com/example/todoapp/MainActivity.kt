package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import com.example.todoapp.data.TodoFilter
import com.example.todoapp.data.TodoItem
import com.example.todoapp.data.TodoState
import com.example.todoapp.ui.components.TodoDialog
import com.example.todoapp.ui.components.AddTodoFab
import com.example.todoapp.ui.components.DeleteConfirmationDialog
import com.example.todoapp.ui.components.FilterChips
import com.example.todoapp.ui.components.TodoItem
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.example.todoapp.viewmodel.TodoViewModel
import com.example.todoapp.ui.components.TodoDetailDialog

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                TodoApp(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(viewModel: TodoViewModel) {
    val state by viewModel.state.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var todoToEdit by remember { mutableStateOf<TodoItem?>(null) }
    var todoToDelete by remember { mutableStateOf<TodoItem?>(null) }
    var todoToShow by remember { mutableStateOf<TodoItem?>(null) }
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TodoTopBar()
        },
        floatingActionButton = {
            AddTodoFab(onAdd = { showAddDialog = true })
        }
    ) { padding ->
        TodoContent(
            modifier = Modifier.padding(padding),
            state = state,
            onToggleTodo = viewModel::toggleTodo,
            onDeleteTodo = { todo -> todoToDelete = todo },
            onEditTodo = { todo -> todoToEdit = todo },
            onShowDetails = { todo -> todoToShow = todo },
            onFilterSelected = viewModel::updateFilter
        )

        if (showAddDialog || todoToEdit != null) {
            TodoDialog(
                todoToEdit = todoToEdit,
                onDismiss = { 
                    showAddDialog = false
                    todoToEdit = null
                },
                onConfirm = { title, description ->
                    if (todoToEdit != null) {
                        viewModel.updateTodo(todoToEdit!!.id, title, description)
                    } else {
                        viewModel.addTodo(title, description)
                    }
                }
            )
        }

        todoToDelete?.let { todo ->
            DeleteConfirmationDialog(
                onDismiss = { todoToDelete = null },
                onConfirm = { 
                    viewModel.deleteTodo(todo.id)
                }
            )
        }

        todoToShow?.let { todo ->
            TodoDetailDialog(
                todo = todo,
                onDismiss = { todoToShow = null },
                onEdit = {
                    todoToEdit = todo
                    todoToShow = null
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.nav_bar),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier.shadow(elevation = 5.dp)
    )
}

@Composable
fun TodoContent(
    modifier: Modifier = Modifier,
    state: TodoState,
    onToggleTodo: (Int) -> Unit,
    onDeleteTodo: (TodoItem) -> Unit,
    onEditTodo: (TodoItem) -> Unit,
    onShowDetails: (TodoItem) -> Unit,
    onFilterSelected: (TodoFilter) -> Unit
) {
    Column(modifier = modifier) {
        FilterChips(
            selectedFilter = state.filter,
            onFilterSelected = onFilterSelected
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = state.items.filter {
                    when (state.filter) {
                        TodoFilter.All -> true
                        TodoFilter.Active -> !it.isCompleted
                        TodoFilter.Completed -> it.isCompleted
                    }
                },
                key = { it.id }
            ) { todo ->
                TodoItem(
                    todo = todo,
                    onToggleCompleted = { onToggleTodo(todo.id) },
                    onDelete = { onDeleteTodo(todo) },
                    onEdit = { onEditTodo(todo) },
                    onShowDetails = { onShowDetails(todo) }
                )
            }
        }
    }
}