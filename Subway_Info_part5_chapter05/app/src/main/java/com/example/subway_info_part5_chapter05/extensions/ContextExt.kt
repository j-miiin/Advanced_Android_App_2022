package com.example.subway_info_part5_chapter05.extensions

import android.content.Context
import androidx.annotation.Px

@Px
fun Context.dip(dipValue: Float) = (dipValue * resources.displayMetrics.density).toInt()