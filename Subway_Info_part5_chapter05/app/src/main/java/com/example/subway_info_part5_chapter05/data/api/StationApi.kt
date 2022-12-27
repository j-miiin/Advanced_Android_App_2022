package com.example.subway_info_part5_chapter05.data.api

import com.example.subway_info_part5_chapter05.data.db.entity.StationEntity
import com.example.subway_info_part5_chapter05.data.db.entity.SubwayEntity

interface StationApi {

    suspend fun getStationDataUpdatedTimeMillis(): Long

    suspend fun getStationSubways(): List<Pair<StationEntity, SubwayEntity>>
}