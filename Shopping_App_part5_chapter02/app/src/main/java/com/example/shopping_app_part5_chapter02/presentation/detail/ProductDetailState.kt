package com.example.shopping_app_part5_chapter02.presentation.detail

import com.example.shopping_app_part5_chapter02.data.entity.product.ProductEntity

sealed class ProductDetailState {

    object UnInitialized: ProductDetailState()

    object Loading: ProductDetailState()

    data class Success(
        val productEntity: ProductEntity
    ): ProductDetailState()

    object Order: ProductDetailState()

    object Error: ProductDetailState()
}