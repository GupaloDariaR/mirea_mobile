package ru.mirea.gupalodr.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mirea.gupalodr.lesson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextMirea.setText("Мой номер по списку №6")
        binding.buttonMirea.setOnClickListener {
            binding.textViewMirea.text = "Я студент МИРЭА"
        }
    }
}