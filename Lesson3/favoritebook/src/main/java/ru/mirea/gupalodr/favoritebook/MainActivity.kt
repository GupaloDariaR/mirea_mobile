package ru.mirea.gupalodr.favoritebook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY = "book_name"
        const val USER_MESSAGE = "MESSAGE"
    }

    private lateinit var  activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var textViewUserBook: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewUserBook = findViewById(R.id.textViewBook)
        button = findViewById(R.id.button)

        val callback = ActivityResultCallback<ActivityResult> {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val userBook = data?.getStringExtra(USER_MESSAGE)
                textViewUserBook.text = String.format("Название Вашей любимой книги: %s", userBook)

            }
        }
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            callback
        )
        
        button.setOnClickListener {
            val intent = Intent(this, ShareActivity::class.java)
            intent.putExtra(KEY, "Метро")
            activityResultLauncher.launch(intent)
        }
    }
}