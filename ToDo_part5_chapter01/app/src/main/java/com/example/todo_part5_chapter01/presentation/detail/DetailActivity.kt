package com.example.todo_part5_chapter01.presentation.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.todo_part5_chapter01.R
import com.example.todo_part5_chapter01.databinding.ActivityDetailBinding
import com.example.todo_part5_chapter01.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class DetailActivity : BaseActivity<DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModel {
        parametersOf(
            intent.getSerializableExtra(DETAIL_MODE_KEY),
            intent.getLongExtra(TODO_ID_KEY, 1)
        )
    }

    companion object {
        const val TODO_ID_KEY = "ToDoId"
        const val DETAIL_MODE_KEY = "DetailMode"
        const val FETCH_REQUEST_CODE = 10

        fun getIntent(context: Context, detailMode: DetailMode) = Intent(context, DetailActivity::class.java).apply {
            putExtra(DETAIL_MODE_KEY, detailMode)
        }

        fun getIntent(context: Context, id: Long, detailMode: DetailMode) = Intent(context, DetailActivity::class.java).apply {
            putExtra(TODO_ID_KEY, id)
            putExtra(DETAIL_MODE_KEY, detailMode)
        }
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResult(Activity.RESULT_OK)
    }

    override fun observeData() = viewModel.toDoDetailLiveData.observe(this){
        when (it) {
            is ToDoDetailState.Uninitialized -> {

            }
            is ToDoDetailState.Loading -> {

            }
            is ToDoDetailState.Success -> {

            }
            is ToDoDetailState.Modify -> {

            }
            is ToDoDetailState.Delete -> {

            }
            is ToDoDetailState.Error -> {

            }
            is ToDoDetailState.Write -> {

            }
        }
    }
}