package ru.mirea.gupalodr.internalfilestorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.mirea.gupalodr.internalfilestorage.databinding.ActivityMainBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var fileName = "memorable_dates.txt";
    private lateinit var editText : EditText;
    private lateinit var button : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        editText.setText(getTextFromFile());

        button.setOnClickListener {
            val outputStream: FileOutputStream
            try {
                outputStream = openFileOutput(fileName, MODE_PRIVATE)
                outputStream.write(editText.text.toString().toByteArray())
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTextFromFile(): String? {
        var fin: FileInputStream? = null
        try {
            fin = openFileInput(fileName)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            return bytes.toString()
        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            try {
                if (fin != null)
                    fin.close()
            } catch (ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
        return null
    }
}