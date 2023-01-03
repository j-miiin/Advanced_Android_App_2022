package com.example.delivery_service_part5_chapter06.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShippingCompany(
    @PrimaryKey
    val code: String,
    val name: String
)
