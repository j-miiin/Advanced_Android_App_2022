package com.example.delivery_service_part5_chapter06.data.repository

import com.example.delivery_service_part5_chapter06.data.entity.ShippingCompany

interface ShippingCompanyRepository {

    suspend fun getShippingCompanies(): List<ShippingCompany>
}