package com.example.delivery_service_part5_chapter06.data.entity

import androidx.room.Embedded
import androidx.room.Entity

@Entity(primaryKeys = ["invoice", "code"])
data class TrackingItem(
    val invoice: String,
    @Embedded val company: ShippingCompany
)
