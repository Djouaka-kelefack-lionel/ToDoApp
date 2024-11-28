package com.example.todoapp.data

sealed class TodoFilter {
    object All : TodoFilter()
    object Active : TodoFilter()
    object Completed : TodoFilter()
}

data class TodoState(
    val items: List<TodoItem> = emptyList(),
    val filter: TodoFilter = TodoFilter.All
) 