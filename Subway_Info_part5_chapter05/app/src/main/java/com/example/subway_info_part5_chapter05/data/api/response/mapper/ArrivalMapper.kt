package com.example.subway_info_part5_chapter05.data.api.response.mapper

import android.util.Log
import com.example.subway_info_part5_chapter05.data.api.response.RealtimeArrival
import com.example.subway_info_part5_chapter05.domain.ArrivalInformation
import com.example.subway_info_part5_chapter05.domain.Subway
import java.text.SimpleDateFormat
import java.util.*

private val apiDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.'0'", Locale.KOREA)
private val dataFormat = SimpleDateFormat("HH:mm:ss", Locale.KOREA)

private const val INVALID_FIELD = "-"

fun RealtimeArrival.toArrivalInformation(): ArrivalInformation {

    Log.d("data", arvlMsg2.toString())
    return ArrivalInformation(
        subway = Subway.findById(subwayId),
        direction = trainLineNm?.split("-")
            ?.get(1)
            ?.trim()
            ?: INVALID_FIELD,
        destination = bstatnNm ?: INVALID_FIELD,
        message = arvlMsg2
            ?.replace(statnNm.toString(), "당역")
            ?.replace("[\\[\\]]".toRegex(), "") ?: INVALID_FIELD,
        updatedAt = recptnDt
            ?.let { apiDateFormat.parse(it) }
            ?.let { dataFormat.format(it) }
            ?: INVALID_FIELD
    )
}

fun List<RealtimeArrival>.toArrivalInformation(): List<ArrivalInformation> =
    map { it.toArrivalInformation() }