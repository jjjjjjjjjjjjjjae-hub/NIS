package com.example.nis.utils

import android.content.Context
import android.provider.Settings

object PermissionUtils {

    fun canDrawOverlay(context: Context): Boolean {
        return Settings.canDrawOverlays(context)
    }
}
