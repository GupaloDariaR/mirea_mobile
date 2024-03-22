package ru.mirea.gupalodr.notificationapp

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var createNotification: Button
    private val PermissionCode = 200
    private val CHANNEL_ID = "com.mirea.asd.notification.ANDROID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity::class.simpleName, "Разрешения получены")
        } else {
            Log.d(MainActivity::class.simpleName, "Нет разрешений!")
            ActivityCompat.requestPermissions(this, arrayOf(POST_NOTIFICATIONS), PermissionCode)
        }

        createNotification = findViewById(R.id.createNotification)
        createNotification.setOnClickListener(this::newMessageNotification)
    }

    fun newMessageNotification(view: View) {
        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Congratulation!")
            .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setContentTitle("Mirea")

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, "Gupalo Daria Romanovna Notification", importance)
        channel.description = "MIREA Channel"

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1, builder.build())
    }
}