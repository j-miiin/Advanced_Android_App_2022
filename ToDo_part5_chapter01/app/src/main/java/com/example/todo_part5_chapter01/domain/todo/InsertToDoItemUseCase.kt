package com.example.todo_part5_chapter01.domain.todo

import com.example.todo_part5_chapter01.data.entity.ToDoEntity
import com.example.todo_part5_chapter01.data.repository.ToDoRepository
import com.example.todo_part5_chapter01.domain.UseCase

internal class InsertToDoItemUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(toDoItem: ToDoEntity) {
        return toDoRepository.insertToDoItem(toDoItem)
    }
}