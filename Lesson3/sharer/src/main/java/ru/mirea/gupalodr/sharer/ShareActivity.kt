package ru.mirea.gupalodr.sharer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_TEXT, "Mirea")
        startActivity(Intent.createChooser(intent, "Выбор за вами!"))
    }
}