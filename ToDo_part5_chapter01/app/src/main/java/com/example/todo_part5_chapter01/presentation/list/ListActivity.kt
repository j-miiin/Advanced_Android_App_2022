package com.example.todo_part5_chapter01.presentation.list

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_part5_chapter01.databinding.ActivityListBinding
import com.example.todo_part5_chapter01.presentation.BaseActivity
import com.example.todo_part5_chapter01.presentation.detail.DetailMode
import com.example.todo_part5_chapter01.presentation.view.ToDoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

internal class ListActivity : BaseActivity<ListViewModel>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private lateinit var binding: ActivityListBinding

    private var adapter = ToDoAdapter()

    override val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViews(binding: ActivityListBinding) = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener {
            viewModel.fetchData()
        }

        addToDoButton.setOnClickListener {
//            startActivityForResult(
//                DetailActivity.getIntent(this@ListActivity, DetailMode.WRITE),
//                DetailActivity.FETCH_REQUEST_CODE
//            )
        }
    }

    override fun observeData() {
        viewModel.toDoListLiveData.observe(this) {
            when (it) {
                is ToDoListState.UnInitialized -> {
                    initViews(binding)
                }
                is ToDoListState.Loading -> {
                    handleLoadingState()
                }
                is ToDoListState.Success -> {
                    handleSuccessState(it)
                }
                is ToDoListState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        refreshLayout.isRefreshing = true
    }

    private fun handleSuccessState(state: ToDoListState.Success) = with(binding) {
        refreshLayout.isEnabled = state.toDoList.isNotEmpty()
        refreshLayout.isRefreshing = false

        if (state.toDoList.isEmpty()) {
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        } else {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setToDoList(
                state.toDoList,
                toDoCheckListener = {
//                    startActivityForResult(
//                        DetailActivity.getIntent(this@ListActivity, it.id, DetailMode.DETAIL),
//                        DetailActivity.FETCH_REQUEST_CODE
//                    )
                }, toDoItemClickListener = {
                    viewModel.updateEntity(it)
                }
            )
        }
    }

    private fun handleErrorState() {
        Toast.makeText(this, "문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }
}