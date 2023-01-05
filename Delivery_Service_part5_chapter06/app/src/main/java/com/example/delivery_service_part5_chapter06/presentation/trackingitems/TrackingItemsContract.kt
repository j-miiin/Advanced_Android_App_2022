package com.example.delivery_service_part5_chapter06.presentation.trackingitems

import com.example.delivery_service_part5_chapter06.data.entity.TrackingInformation
import com.example.delivery_service_part5_chapter06.data.entity.TrackingItem
import com.example.delivery_service_part5_chapter06.presentation.BasePresenter
import com.example.delivery_service_part5_chapter06.presentation.BaseView

class TrackingItemsContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription()

        fun showTrackingItemInformation(trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>)
    }

    interface Presenter : BasePresenter {
        var trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>

        fun refresh()
    }
}