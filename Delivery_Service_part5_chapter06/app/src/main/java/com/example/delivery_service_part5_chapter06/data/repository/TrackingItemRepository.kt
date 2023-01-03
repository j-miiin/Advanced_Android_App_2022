package com.example.delivery_service_part5_chapter06.data.repository

import com.example.delivery_service_part5_chapter06.data.entity.TrackingInformation
import com.example.delivery_service_part5_chapter06.data.entity.TrackingItem

interface TrackingItemRepository {

    suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>>
}