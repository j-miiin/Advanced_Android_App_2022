package com.example.shopping_app_part5_chapter02.domain

import com.example.shopping_app_part5_chapter02.data.entity.product.ProductEntity
import com.example.shopping_app_part5_chapter02.data.repository.ProductRepository

internal class GetProductItemUseCase(
    private val productRepository: ProductRepository
): UseCase {

    suspend operator fun invoke(productId: Long): ProductEntity? {
        return productRepository.getProductItem(productId)
    }
}