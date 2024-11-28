package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.todoapp.data.TodoItem
import com.example.todoapp.data.TodoState
import com.example.todoapp.data.TodoFilter

class TodoViewModel : ViewModel() {
    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    private var nextId = 1

    fun addTodo(title: String, description: String = "") {
        val newItem = TodoItem(
            id = nextId++,
            title = title,
            description = description
        )
        _state.update { currentState ->
            currentState.copy(items = currentState.items + newItem)
        }
    }

    fun toggleTodo(id: Int) {
        _state.update { currentState ->
            val updatedItems = currentState.items.map { item ->
                if (item.id == id) item.copy(isCompleted = !item.isCompleted)
                else item
            }
            currentState.copy(items = updatedItems)
        }
    }

    fun deleteTodo(id: Int) {
        _state.update { currentState ->
            currentState.copy(items = currentState.items.filterNot { it.id == id })
        }
    }

    fun updateFilter(filter: TodoFilter) {
        _state.update { it.copy(filter = filter) }
    }

    fun updateTodo(id: Int, title: String, description: String) {
        _state.update { currentState ->
            val updatedItems = currentState.items.map { item ->
                if (item.id == id) {
                    item.copy(title = title, description = description)
                } else item
            }
            currentState.copy(items = updatedItems)
        }
    }
} 