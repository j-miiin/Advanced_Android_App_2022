package com.example.subway_info_part5_chapter05.presentation.stations

import com.example.subway_info_part5_chapter05.domain.Station
import com.example.subway_info_part5_chapter05.presentation.BasePresenter
import com.example.subway_info_part5_chapter05.presentation.BaseView

interface StationsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showStations(stations: List<Station>)
    }

    interface Presenter : BasePresenter {
        fun filterStations(query: String)
    }
}