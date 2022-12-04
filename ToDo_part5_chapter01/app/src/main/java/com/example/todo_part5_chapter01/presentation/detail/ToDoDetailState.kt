package com.example.todo_part5_chapter01.presentation.detail

import com.example.todo_part5_chapter01.data.entity.ToDoEntity

sealed class ToDoDetailState {

    object Uninitialized: ToDoDetailState()

    object Loading: ToDoDetailState()

    data class Success(
        val toDoItem: ToDoEntity
    ): ToDoDetailState()

    object Delete: ToDoDetailState()

    object Modify: ToDoDetailState()

    object Error: ToDoDetailState()

    object Write: ToDoDetailState()
}