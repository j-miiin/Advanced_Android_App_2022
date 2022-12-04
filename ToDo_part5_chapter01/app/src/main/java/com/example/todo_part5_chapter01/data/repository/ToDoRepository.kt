package com.example.todo_part5_chapter01.data.repository

import com.example.todo_part5_chapter01.data.entity.ToDoEntity

// 1. insetToDoList
// 2. getToDoList
// 3. updateToDoItem

interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoItem(toDoItem: ToDoEntity)

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)

    suspend fun updateToDoItem(toDoItem: ToDoEntity): Boolean

    suspend fun getToDoItem(itemId: Long): ToDoEntity?

    suspend fun deleteAll()

    suspend fun deleteToDoItem(id: Long): Boolean
}