package com.example.nis.utils

import android.util.Log

object Logger {

    private const val TAG = "NIS"

    fun d(message: String) {
        Log.d(TAG, message)
    }

    fun e(message: String, error: Throwable? = null) {
        Log.e(TAG, message, error)
    }
}
