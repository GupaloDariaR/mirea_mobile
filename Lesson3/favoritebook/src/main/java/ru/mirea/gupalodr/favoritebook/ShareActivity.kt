package ru.mirea.gupalodr.favoritebook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ShareActivity : AppCompatActivity() {
    private lateinit var developerBook: TextView
    private lateinit var userBook: EditText
    private  lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        developerBook = findViewById(R.id.developerBook)
        userBook = findViewById(R.id.userBook)
        button = findViewById(R.id.button2)

        // Получение данных из MainActivity
        val extras = intent.extras
        if (extras != null) {
            val bookTitle = extras.getString(MainActivity.KEY)
            developerBook.text = String.format("Любимая книга разработчика – %s", bookTitle)

        }

        // Отправка введенных пользователем данных по нажатию на кнопку
        button.setOnClickListener {
            val data = Intent()
            val text = userBook.text.toString()
            data.putExtra(MainActivity.USER_MESSAGE, text)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}