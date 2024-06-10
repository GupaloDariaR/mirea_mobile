package ru.mirea.gupalodr.looper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import ru.mirea.gupalodr.looper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainThreadHandler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(MainActivity::class.java.simpleName,
                    "Task execute. This is result: " + msg.data.getString("result")
                )
            }
        }
        val myLooper = MyLooper(mainThreadHandler)
        myLooper.start()
        binding.editTextMirea.text = "Мой номер по списку №6"
        binding.buttonMirea.setOnClickListener{
            val age = binding.age.text.toString().toInt()
            val work = binding.work.text.toString()
            val msg = Message.obtain()
            val bundle = Bundle()
            bundle.putString("KEY", "mirea")
            bundle.putInt("AGE", age)
            bundle.putString("WORK", work)
            msg.data = bundle
            myLooper.mHandler.sendMessage(msg)
        }
    }
}