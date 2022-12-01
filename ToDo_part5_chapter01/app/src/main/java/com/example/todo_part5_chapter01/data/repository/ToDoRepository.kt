package com.example.todo_part5_chapter01.data.repository

import com.example.todo_part5_chapter01.data.entity.ToDoEntity

// 1. insetToDoList
// 2. getToDoList

interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)
}