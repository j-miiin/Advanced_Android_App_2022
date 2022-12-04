package com.example.todo_part5_chapter01.viewmodel.todo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.todo_part5_chapter01.ViewModelTest
import com.example.todo_part5_chapter01.data.entity.ToDoEntity
import com.example.todo_part5_chapter01.domain.todo.InsertToDoItemUseCase
import com.example.todo_part5_chapter01.presentation.detail.DetailMode
import com.example.todo_part5_chapter01.presentation.detail.DetailViewModel
import com.example.todo_part5_chapter01.presentation.detail.ToDoDetailState
import com.example.todo_part5_chapter01.presentation.list.ListViewModel
import com.example.todo_part5_chapter01.presentation.list.ToDoListState
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

// [DetailViewModel] 테스트
// 1. initData()
// 2. test viewModel fetch
// 3. test delete todo
// 4. test update todo

internal class DetailViewModelTest: ViewModelTest() {

    private val id = 1L

    private val detailViewModel by inject<DetailViewModel>{ parametersOf(DetailMode.DETAIL, id) }
    private val listViewModel by inject<ListViewModel>()

    private val insertToDoItemUseCase: InsertToDoItemUseCase by inject()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoItemUseCase(todo)
    }

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()
        
        detailViewModel.fetchData()
        
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.Uninitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )
    }

    @Test
    fun `test delete todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.deleteTodo()

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.Uninitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Delete
            )
        )

        val listTestObservable = listViewModel.toDoListLiveData.test()
        listViewModel.fetchData()

        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )
    }

    @Test
    fun `test update todo`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()

        val updateTitle = "title 1 update"
        val updateDescription = "description 1 update"

        val updateToDo = todo.copy(
            title = updateTitle,
            description = updateDescription
        )

        detailViewModel.writeToDo(
            title = updateTitle,
            description = updateDescription
        )

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.Uninitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(updateToDo)
            )
        )
    }
}