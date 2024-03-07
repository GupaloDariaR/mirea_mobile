package ru.mirea.gupalodr.buttonclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvOut: TextView
    private lateinit var btnWhoAmI: Button
    private lateinit var btnItIsNotMe: Button
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvOut = findViewById(R.id.tvOut)
        btnWhoAmI = findViewById(R.id.btnWhoAmI)
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe)
        checkBox = findViewById(R.id.checkBox)

        btnWhoAmI.setOnClickListener {
            tvOut.text = "Мой номер по списку № 6"
            checkBox.isChecked = true
        }
    }

    fun onClickItIsNotMe(view: View) {
        tvOut.text = "Это не я сделал"
        checkBox.isChecked = false
    }
}