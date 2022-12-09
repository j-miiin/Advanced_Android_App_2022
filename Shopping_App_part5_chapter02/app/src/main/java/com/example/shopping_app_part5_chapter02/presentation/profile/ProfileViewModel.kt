package com.example.shopping_app_part5_chapter02.presentation.profile

import androidx.lifecycle.viewModelScope
import com.example.shopping_app_part5_chapter02.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProfileViewModel: BaseViewModel(){
    override fun fetchData(): Job = viewModelScope.launch {

    }
}