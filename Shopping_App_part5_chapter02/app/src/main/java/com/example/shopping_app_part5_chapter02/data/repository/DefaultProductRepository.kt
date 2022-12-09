package com.example.shopping_app_part5_chapter02.data.repository

import com.example.shopping_app_part5_chapter02.data.entity.product.ProductEntity
import com.example.shopping_app_part5_chapter02.data.network.ProductApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultProductRepository(
    private val productApi: ProductApiService,
    private val ioDispatcher: CoroutineDispatcher
): ProductRepository {

    override suspend fun getProductList(): List<ProductEntity> = withContext(ioDispatcher) {
        val response = productApi.getProducts()
        return@withContext if (response.isSuccessful) {
            response.body()?.items?.map { it.toEntity() } ?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getLocalProductList(): List<ProductEntity> = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun insertProductItem(productItem: ProductEntity): Long = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun insertProductList(productList: List<ProductEntity>) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProductItem(productItem: ProductEntity) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductItem(itemId: Long): ProductEntity? = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductItem(id: Long) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }
}