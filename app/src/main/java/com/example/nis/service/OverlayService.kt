package com.example.nis.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.nis.R
import com.example.nis.manager.OverlayManager

class OverlayService : Service() {

    private lateinit var overlayManager: OverlayManager

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("NIS")
            .setContentText("Overlay Service жұмыс істеп тұр")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            .build()

        startForeground(NOTIFICATION_ID, notification)

        overlayManager = OverlayManager(this) {
            stopSelf()
        }

        overlayManager.showOverlay()
    }

    override fun onDestroy() {
        overlayManager.hideOverlay()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Overlay Service",
                NotificationManager.IMPORTANCE_LOW
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "overlay_service"
        private const val NOTIFICATION_ID = 1001
    }
}
