package com.example.todo_part5_chapter01.di

import android.content.Context
import androidx.room.Room
import com.example.todo_part5_chapter01.data.local.db.ToDoDatabase
import com.example.todo_part5_chapter01.data.repository.DefaultToDoRepository
import com.example.todo_part5_chapter01.data.repository.ToDoRepository
import com.example.todo_part5_chapter01.domain.todo.*
import com.example.todo_part5_chapter01.domain.todo.DeleteAllToDoItemUseCase
import com.example.todo_part5_chapter01.domain.todo.DeleteToDoItemUseCase
import com.example.todo_part5_chapter01.domain.todo.GetToDoItemUseCase
import com.example.todo_part5_chapter01.domain.todo.GetToDoListUseCase
import com.example.todo_part5_chapter01.domain.todo.InsertToDoItemUseCase
import com.example.todo_part5_chapter01.domain.todo.InsertToDoListUseCase
import com.example.todo_part5_chapter01.domain.todo.UpdateToDoUseCase
import com.example.todo_part5_chapter01.presentation.detail.DetailMode
import com.example.todo_part5_chapter01.presentation.detail.DetailViewModel
import com.example.todo_part5_chapter01.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // viewModel
    viewModel { ListViewModel(get(), get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) ->
        DetailViewModel(
            detailMode = detailMode,
            id = id,
            get(),
            get(),
            get(),
            get()
        )
    }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }

    // Repository
    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }
}

internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()