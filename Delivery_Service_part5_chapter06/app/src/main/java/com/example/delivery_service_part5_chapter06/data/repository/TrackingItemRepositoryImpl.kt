package com.example.delivery_service_part5_chapter06.data.repository

import com.example.delivery_service_part5_chapter06.data.api.SweetTrackerApi
import com.example.delivery_service_part5_chapter06.data.db.TrackingItemDao
import com.example.delivery_service_part5_chapter06.data.entity.TrackingInformation
import com.example.delivery_service_part5_chapter06.data.entity.TrackingItem
import kotlinx.coroutines.CoroutineDispatcher

class TrackingItemRepositoryImpl(
    private val trackerApi: SweetTrackerApi,
    private val trackingItemDao: TrackingItemDao,
    private val dispatcher: CoroutineDispatcher
) : TrackingItemRepository {

    override suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>> {
        trackingItemDao.getAll()
            .mapNotNull { trackingItem ->
                val relatedTrackingInfo = trackerApi.getTrackingInformation(
                    trackingItem.company.code,
                    trackingItem.invoice
                ).body()

                if (relatedTrackingInfo?.invoiceNo.isNullOrBlank()) {
                    null
                } else {
                    trackingItem to relatedTrackingInfo!!
                }
            }
            .sortedWith(
                compareBy(
                    { it.second.level },
                    { -(it.second.lastDetail?.time ?: Long.MAX_VALUE) }
                )
            )
    }
}