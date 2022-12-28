package com.example.subway_info_part5_chapter05.data.api

import com.example.subway_info_part5_chapter05.data.db.entity.StationEntity
import com.example.subway_info_part5_chapter05.data.db.entity.SubwayEntity
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class StationStorageApi(
    firebaseStorage: FirebaseStorage
) : StationApi {

    private val sheetReference = firebaseStorage.reference.child(STATION_DATA_FILE_NAME)

    override suspend fun getStationDataUpdatedTimeMillis(): Long =
        sheetReference.metadata.await().updatedTimeMillis

    override suspend fun getStationSubways(): List<Pair<StationEntity, SubwayEntity>> {
        val downloadSizeBytes = sheetReference.metadata.await().sizeBytes
        val byteArray = sheetReference.getBytes(downloadSizeBytes).await()

        return byteArray.decodeToString()
            .lines()    // 1002, 건대입구
            .drop(1)    // header 영역 날리기 (맨 윗줄 - SUBWAY_ID, STATN_NM)
            .map { it.split(",") }
            .map { StationEntity(it[1]) to SubwayEntity(it[0].toInt()) }
    }

    companion object {
        private const val STATION_DATA_FILE_NAME = "station_data.csv"
    }
}