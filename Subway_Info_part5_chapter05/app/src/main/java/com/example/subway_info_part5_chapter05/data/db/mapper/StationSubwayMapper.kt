package com.example.subway_info_part5_chapter05.data.db.mapper

import com.example.subway_info_part5_chapter05.data.db.entity.StationWithSubwayEntity
import com.example.subway_info_part5_chapter05.data.db.entity.SubwayEntity
import com.example.subway_info_part5_chapter05.domain.Station
import com.example.subway_info_part5_chapter05.domain.Subway

fun StationWithSubwayEntity.toStation() = Station(
    name = station.stationName,
    isFavorited = station.isFavorited,
    connectedSubways = subways.toSubways()
)

fun List<StationWithSubwayEntity>.toStations() = map { it.toStation() }

fun List<SubwayEntity>.toSubways(): List<Subway> = map { Subway.findById(it.subwayId) }