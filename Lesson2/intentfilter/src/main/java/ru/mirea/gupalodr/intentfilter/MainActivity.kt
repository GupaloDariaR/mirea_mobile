package ru.mirea.gupalodr.intentfilter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.openSite).setOnClickListener {
            val address = Uri.parse("https://www.mirea.ru/")
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
            startActivity(openLinkIntent)
        }

        findViewById<Button>(R.id.share).setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain";
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Гупало Дарья Романовна")
            startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"))
        }
    }
}