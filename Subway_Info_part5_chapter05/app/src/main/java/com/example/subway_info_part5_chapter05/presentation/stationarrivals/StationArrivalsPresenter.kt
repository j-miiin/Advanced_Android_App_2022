package com.example.subway_info_part5_chapter05.presentation.stationarrivals

import com.example.subway_info_part5_chapter05.data.repository.StationRepository
import com.example.subway_info_part5_chapter05.domain.Station
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class StationArrivalsPresenter(
    private val view: StationArrivalsContract.View,
    private val station: Station,
    private val stationRepository: StationRepository
) : StationArrivalsContract.Presenter {

    override val scope = MainScope()

    override fun onViewCreated() {
        fetchStationArrivals()
    }

    override fun onDestroyView() { }

    override fun fetchStationArrivals() {
        scope.launch {
            try {
                view.showLoadingIndicator()
                view.showStationArrivals(stationRepository.getStationArrivals(station.name))
            } catch (e: Exception) {
                e.printStackTrace()
                view.showErrorDescription(e.message ?: "알 수 없는 문제가 발생했어요 😢")
            } finally {
                view.hideLoadingIndicator()
            }
        }
    }

    override fun toggleStationFavorite() {
//        scope.launch {
//            stationRepository.updateStation(station.copy(isFavorited = !station.isFavorited))
//        }
    }
}