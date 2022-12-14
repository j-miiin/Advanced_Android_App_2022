package com.example.subway_info_part5_chapter05.data.repository

import com.example.subway_info_part5_chapter05.data.api.StationApi
import com.example.subway_info_part5_chapter05.data.api.StationArrivalsApi
import com.example.subway_info_part5_chapter05.data.api.response.mapper.toArrivalInformation
import com.example.subway_info_part5_chapter05.data.api.response.mapper.toStationEntity
import com.example.subway_info_part5_chapter05.data.api.response.mapper.toStations
import com.example.subway_info_part5_chapter05.data.db.StationDao
import com.example.subway_info_part5_chapter05.data.db.entity.StationSubwayCrossRefEntity
import com.example.subway_info_part5_chapter05.data.preference.PreferenceManager
import com.example.subway_info_part5_chapter05.domain.ArrivalInformation
import com.example.subway_info_part5_chapter05.domain.Station
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class StationRepositoryImpl(
    private val stationArrivalsApi: StationArrivalsApi,
    private val stationApi: StationApi,
    private val stationDao: StationDao,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : StationRepository {

    override val stations: Flow<List<Station>> =
        stationDao.getStationWithSubways()
            .distinctUntilChanged()
            .map { stations -> stations.toStations().sortedByDescending { it.isFavorited } }
            .flowOn(dispatcher) // 어떤 thread에서 데이터가 흐를 것인가

    override suspend fun refreshStations() = withContext(dispatcher) {
        val fileUpdatedTimeMillis = stationApi.getStationDataUpdatedTimeMillis()
        val lastDatabaseUpdatedTimeMills = preferenceManager.getLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS)

        if (lastDatabaseUpdatedTimeMills == null || fileUpdatedTimeMillis > lastDatabaseUpdatedTimeMills) {
            val stationSubways = stationApi.getStationSubways()
            stationDao.insertStations(stationSubways.map { it.first })
            stationDao.insertSubways(stationSubways.map { it.second })
            stationDao.insertCrossReferences(
                stationSubways.map { (station, subway) ->
                    StationSubwayCrossRefEntity(
                        station.stationName,
                        subway.subwayId
                    )
                }
            )
            preferenceManager.putLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS, fileUpdatedTimeMillis)
        }
    }

    override suspend fun getStationArrivals(stationName: String): List<ArrivalInformation> = withContext(dispatcher) {
        stationArrivalsApi.getRealtimeStationArrivals(stationName)
            .body()
            ?.realtimeArrivalList
            ?.toArrivalInformation()
            ?.distinctBy { it.direction }
            ?.sortedBy { it.subway }
            ?: throw RuntimeException("도착 정보를 불러오는 데에 실패했습니다.")
    }

    override suspend fun updateStation(station: Station) {
        stationDao.updateStation(station.toStationEntity())
    }


    companion object {
        private const val KEY_LAST_DATABASE_UPDATED_TIME_MILLIS = "KEY_LAST_DATABASE_UPDATED_TIME_MILLIS"
    }
}
