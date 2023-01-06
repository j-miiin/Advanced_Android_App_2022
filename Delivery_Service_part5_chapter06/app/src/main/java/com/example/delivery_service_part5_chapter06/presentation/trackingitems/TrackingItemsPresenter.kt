package com.example.delivery_service_part5_chapter06.presentation.trackingitems

import com.example.delivery_service_part5_chapter06.data.entity.TrackingInformation
import com.example.delivery_service_part5_chapter06.data.entity.TrackingItem
import com.example.delivery_service_part5_chapter06.data.repository.TrackingItemRepository
import kotlinx.coroutines.launch

class TrackingItemsPresenter(
    private val view: TrackingItemsContract.View,
    private val trackingItemRepository: TrackingItemRepository
) : TrackingItemsContract.Presenter {

    override var trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>> = emptyList()

    init {
        scope.launch {
            trackingItemRepository
                .trackingItems
                .collect { refresh() }
        }
    }

    override fun onViewCreated() {
        fetchTrackingInformation()
    }

    override fun onDestroyView() { }

    override fun refresh() {
        fetchTrackingInformation(true)
    }

    private fun fetchTrackingInformation(forceFetch: Boolean = false) = scope.launch {
        try {
            view.showLoadingIndicator()

            if (trackingItemInformation.isEmpty() || forceFetch) {
                trackingItemInformation = trackingItemRepository.getTrackingItemInformation()
            }

            if (trackingItemInformation.isEmpty()) {
                view.showNoDataDescription()
            } else {
                view.showTrackingItemInformation(trackingItemInformation)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            view.hideLoadingIndicator()
        }
    }
}