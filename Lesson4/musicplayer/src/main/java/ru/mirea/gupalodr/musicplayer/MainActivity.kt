package ru.mirea.gupalodr.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mirea.gupalodr.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.songName.text = "Отрицательный герой"
        binding.authorName.text = "Нервы"
        binding.endTime.text = "3:00"
    }
}