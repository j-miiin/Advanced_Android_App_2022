package com.example.delivery_service_part5_chapter06.data.entity

import com.google.gson.annotations.SerializedName

data class ShippingCompanies(
    @SerializedName("Company")
    val shippingCompanies: List<ShippingCompany>? = null
)
