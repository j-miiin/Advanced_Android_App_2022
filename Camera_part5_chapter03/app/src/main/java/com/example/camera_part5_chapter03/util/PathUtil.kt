package com.example.camera_part5_chapter03.util

import android.app.Activity
import com.example.camera_part5_chapter03.R
import java.io.File

object PathUtil {

    fun getOutputDirectory(activity: Activity): File = with(activity) {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }
}