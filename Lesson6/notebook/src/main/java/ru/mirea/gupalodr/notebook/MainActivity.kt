package ru.mirea.gupalodr.notebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.EditText
import ru.mirea.gupalodr.notebook.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fileName: EditText
    private lateinit var quote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fileName = binding.fileName
        quote = binding.quote

        binding.saveButton.setOnClickListener {
            saveQuote()
        }

        binding.loadButtoon.setOnClickListener {
            loadQuote()
        }
    }

    private fun loadQuote() {
        if (!isExternalStorageReadable()) return

        val path = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS)
        val file = File(path, fileName.text.toString())
        Log.w("ExternalStorage", file.toString())
        try {
            val fileInputStream = FileInputStream(file.absoluteFile)
            val inputStreamReader = InputStreamReader(fileInputStream, StandardCharsets.UTF_8)
            val lines: MutableList<String> = ArrayList()
            val reader = BufferedReader(inputStreamReader)
            var line = reader.readLine()
            while (line != null) {
                lines.add(line)
                line = reader.readLine()
            }
            quote.setText(java.lang.String.join("", lines))

            Log.w("ExternalStorage",
                String.format("Read	from file %s successful", lines.toString()))
        } catch (e: Exception) {
            Log.w("ExternalStorage",
                String.format("Read	from file %s failed", e.message))
        }
    }

    private fun saveQuote() {
        if (!isExternalStorageWritable()) return

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        Log.d("ExternalStorage", path.toString())
        val file = File(path, fileName.text.toString())
        try {
            Log.d("ExternalStorage", file.toString())
            val fileOutputStream = FileOutputStream(file.absoluteFile)
            val output = OutputStreamWriter(fileOutputStream)
            // Запись строки в файл
            output.write(quote.text.toString())
            // Закрытие потока записи
            output.close()
        } catch (e: IOException) {
            Log.w("ExternalStorage", "Error writing $file", e)
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {
            return true
        } else return false
    }

    private fun isExternalStorageReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state) {
            return true
        } else return false
    }
}