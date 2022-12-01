package com.example.todo_part5_chapter01.di

import com.example.todo_part5_chapter01.data.repository.TestToDoRepository
import com.example.todo_part5_chapter01.data.repository.ToDoRepository
import com.example.todo_part5_chapter01.domain.todo.*
import com.example.todo_part5_chapter01.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // viewModel
    viewModel { ListViewModel(get(), get(), get()) }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepository() }
}