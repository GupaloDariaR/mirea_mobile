package ru.mirea.gupalodr.toastapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var letterCount: Button
    private lateinit var inputText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        letterCount = findViewById(R.id.letterCount)
        inputText = findViewById(R.id.inputText)
        
        letterCount.setOnClickListener {
            val count = inputText.text.length
            Toast.makeText(
                this,
                "СТУДЕНТ № 6\nГРУППА БСБО-10-21\nКоличество символов - $count",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}