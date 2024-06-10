package ru.mirea.gupalodr.serviceapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class PlayerService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    var CHANNEL_ID = "ForegroundServiceChannel"

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            stopForeground(true)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Playing...")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Нервы"))
            .setContentTitle("Отрицательный герой")
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, "Гупало Д.Р. Notification", importance)
        channel.description = "MIREA CHANNEL"
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(channel)
        startForeground(1,  builder.build())

        mediaPlayer = MediaPlayer.create(this, R.raw.song)
    }

    override fun onDestroy() {
        stopForeground(true)
        mediaPlayer.stop()
    }
}