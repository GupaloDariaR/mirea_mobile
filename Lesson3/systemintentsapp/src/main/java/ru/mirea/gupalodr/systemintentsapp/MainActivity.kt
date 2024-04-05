package ru.mirea.gupalodr.systemintentsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var call: Button
    private lateinit var openBrowser: Button
    private lateinit var openMap: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        call = findViewById(R.id.call)
        openBrowser = findViewById(R.id.openBrowser)
        openMap = findViewById(R.id.openMap)

        call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:89811112233")
            startActivity(intent)
        }

        openBrowser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://developer.android.com")
            startActivity(intent)
        }

        openMap.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("geo:55.749479,37.613944")
            startActivity(intent)
        }
    }
}