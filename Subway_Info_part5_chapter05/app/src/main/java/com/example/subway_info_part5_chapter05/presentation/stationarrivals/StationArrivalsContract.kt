package com.example.subway_info_part5_chapter05.presentation.stationarrivals

import com.example.subway_info_part5_chapter05.domain.ArrivalInformation
import com.example.subway_info_part5_chapter05.presentation.BasePresenter
import com.example.subway_info_part5_chapter05.presentation.BaseView

interface StationArrivalsContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showStationArrivals(arrivalInformation: List<ArrivalInformation>)
    }

    interface Presenter : BasePresenter {
        fun fetchStationArrivals()

        fun toggleStationFavorite()
    }
}