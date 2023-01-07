package com.example.delivery_service_part5_chapter06.presentation

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

interface BasePresenter {

    val scope: CoroutineScope

    fun onViewCreated()

    fun onDestroyView()

    @CallSuper
    fun onDestroy() {
        scope.cancel()
    }
}