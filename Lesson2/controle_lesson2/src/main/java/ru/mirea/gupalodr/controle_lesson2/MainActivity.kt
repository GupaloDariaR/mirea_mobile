package ru.mirea.gupalodr.controle_lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var timeDialog: Button
    private lateinit var dateDialog: Button
    private lateinit var progressDialog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeDialog = findViewById(R.id.timeDialog)
        dateDialog = findViewById(R.id.dateDialog)
        progressDialog = findViewById(R.id.progressDialog)

        timeDialog.setOnClickListener(this::showTimeDialog)
        dateDialog.setOnClickListener(this::showDateDialog)
        progressDialog.setOnClickListener(this::showProgressDialog)
    }

    private fun showTimeDialog(view: View) {
        val dialogFragment = MyTimeDialogFragment()
        dialogFragment.show(supportFragmentManager, "mirea")
    }

    private fun showDateDialog(view: View) {
        val dialogFragment = MyDateDialogFragment()
        dialogFragment.show(supportFragmentManager, "mirea")
    }

    private fun showProgressDialog(view: View) {
        val dialogFragment = MyProgressDialogFragment()
        dialogFragment.show(supportFragmentManager, "mirea")
    }

    fun showTime(hour: Int, minute: Int) {
        Snackbar.make(
            findViewById(R.id.rootView),
            "Выбранное время: $hour ч. $minute мин.",
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun showDate(year: Int, month: Int, day: Int) {
        Snackbar.make(
            findViewById(R.id.rootView),
            "Выбранная дата: $day.$month.$year",
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun showProgress() {
        Snackbar.make(
            findViewById(R.id.rootView),
            "Вы закрыли окно загрузки",
            Snackbar.LENGTH_LONG
        ).show()
    }
}