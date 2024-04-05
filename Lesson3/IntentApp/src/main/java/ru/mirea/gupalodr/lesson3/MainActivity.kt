package ru.mirea.gupalodr.lesson3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent: Intent = Intent(this, SecondActivity::class.java)

            val dateInMillis = System.currentTimeMillis()
            val format = "yyyy-MM-dd HH:mm:ss"
            val sdf = SimpleDateFormat(format)
            val dateString: String = sdf.format(Date(dateInMillis))

            intent.putExtra("currentTime", dateString)
            startActivity(intent)
        }
    }
}