package com.example.nis.manager

import android.content.Context
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.util.Log

class OverlayManager(
    private val context: Context,
    private val onCloseRequested: () -> Unit
) {
    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    
    @Volatile private var isViewAttached = false // Multi-threading қауіпсіздігі
    private lateinit var params: WindowManager.LayoutParams

    private val rootLayout: LinearLayout by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(0xCC000000.toInt())
            gravity = Gravity.CENTER
            addView(Button(context).apply { 
                text = "EXIT"
                setOnClickListener { 
                    hideOverlay()
                    onCloseRequested() 
                } 
            })
        }
    }

    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) {}
        override fun onDisplayRemoved(displayId: Int) {}
        override fun onDisplayChanged(displayId: Int) {
            updateLayoutSafely()
        }
    }

    fun showOverlay() {
        if (isViewAttached) return
        params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT, 
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )
        try {
            windowManager.addView(rootLayout, params)
            isViewAttached = true
            displayManager.registerDisplayListener(displayListener, null)
        } catch (e: WindowManager.BadTokenException) {
            Log.e("OverlayManager", "Bad token, overlay permission revoked?", e)
        } catch (e: Exception) {
            Log.e("OverlayManager", "Unknown error adding view", e)
        }
    }

    private fun updateLayoutSafely() {
        if (!isViewAttached) return
        try {
            // Ерекше жағдайды (Exception) қауіпсіз ұстау
            if (rootLayout.windowToken != null) {
                windowManager.updateViewLayout(rootLayout, params)
            }
        } catch (e: IllegalArgumentException) {
            Log.e("OverlayManager", "View not attached to WindowManager", e)
            isViewAttached = false
        } catch (e: Exception) {
            Log.e("OverlayManager", "Failed to update layout", e)
        }
    }

    fun hideOverlay() {
        if (!isViewAttached) return
        try {
            displayManager.unregisterDisplayListener(displayListener)
            if (rootLayout.windowToken != null) {
                windowManager.removeView(rootLayout)
            }
        } catch (e: Exception) {
            Log.e("OverlayManager", "Error removing view", e)
        } finally {
            isViewAttached = false
        }
    }
}
