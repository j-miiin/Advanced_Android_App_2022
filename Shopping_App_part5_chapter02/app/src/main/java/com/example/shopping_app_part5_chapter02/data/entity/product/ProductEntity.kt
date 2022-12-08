package com.example.shopping_app_part5_chapter02.data.entity.product

import java.util.Date

data class ProductEntity(
    val id: Long,
    val createdAt: Date,
    val productName: String,
    val productPrice: Int,
    val productImage: String,
    val productType: String,
    val productIntroductionImage: String
)
