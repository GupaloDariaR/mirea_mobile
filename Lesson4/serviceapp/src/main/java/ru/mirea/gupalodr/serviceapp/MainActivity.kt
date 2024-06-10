package ru.mirea.gupalodr.serviceapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.mirea.gupalodr.serviceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val PermissionCode: Int = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity::class.java.simpleName, "Разрешения получены")
        } else {
            Log.d(MainActivity::class.java.simpleName, "Нет разрешений!")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.FOREGROUND_SERVICE), PermissionCode)
        }

        binding.play.setOnClickListener {
            val serviceIntent = Intent(this, PlayerService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)
        }

        binding.pause.setOnClickListener {
            stopService(Intent(this, PlayerService::class.java))
        }
    }
}