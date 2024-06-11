package ru.mirea.gupalodr.lesson6

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.gupalodr.lesson6.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var group: EditText
    private lateinit var number: EditText
    private lateinit var film: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        group = binding.group
        number = binding.number
        film = binding.film

        val sharedPref = getSharedPreferences("mirea_settings", MODE_PRIVATE)
        group.setText(sharedPref.getString("GROUP", ""))
        number.setText(sharedPref.getString("NUMBER ", ""))
        film.setText(sharedPref.getString("FILM", ""))

        binding.button.setOnClickListener {
            val newSharedPref = getSharedPreferences("mirea_settings", MODE_PRIVATE)
            val editor = newSharedPref.edit()
            editor.putString("GROUP", group.text.toString());
            editor.putString("NUMBER", number.text.toString());
            editor.putString("FILM", film.text.toString());
            editor.apply();
        }
    }
}