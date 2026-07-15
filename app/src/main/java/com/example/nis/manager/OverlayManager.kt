package com.example.nis.manager

import android.content.Context
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout

class OverlayManager(
    private val context: Context,
    private val onCloseRequested: () -> Unit
) {

    private val windowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private val displayManager =
        context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

    @Volatile
    private var isAttached = false

    private lateinit var params: WindowManager.LayoutParams

    private val overlayView: LinearLayout by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(0xCC000000.toInt())

            addView(
                Button(context).apply {
                    text = "EXIT"
                    setOnClickListener {
                        hideOverlay()
                        onCloseRequested()
                    }
                }
            )
        }
    }

    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) = Unit

        override fun onDisplayRemoved(displayId: Int) = Unit

        override fun onDisplayChanged(displayId: Int) {
            updateOverlay()
        }
    }

    fun showOverlay() {
        if (isAttached) return

        params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP
        }

        try {
            windowManager.addView(overlayView, params)
            displayManager.registerDisplayListener(displayListener, null)
            isAttached = true
        } catch (e: Exception) {
            Log.e(TAG, "Unable to show overlay", e)
        }
    }

    private fun updateOverlay() {
        if (!isAttached) return

        try {
            if (overlayView.windowToken != null) {
                windowManager.updateViewLayout(overlayView, params)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Unable to update overlay", e)
        }
    }

    fun hideOverlay() {
        if (!isAttached) return

        try {
            displayManager.unregisterDisplayListener(displayListener)

            if (overlayView.windowToken != null) {
                windowManager.removeView(overlayView)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Unable to remove overlay", e)
        } finally {
            isAttached = false
        }
    }

    companion object {
        private const val TAG = "OverlayManager"
    }
}
