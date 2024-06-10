package ru.mirea.gupalodr.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.mirea.gupalodr.thread.databinding.ActivityMainBinding
import java.util.Arrays
import kotlin.math.round


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val infoTextView = binding.textView
        val mainThread = Thread.currentThread()
        infoTextView.text = "Имя текущего потока: " + mainThread.name
        mainThread.name = "МОЙ НОМЕР ГРУППЫ: БСБО-10-21, НОМЕР ПО СПИСКУ: 6, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Начало"
        infoTextView.append("\nНовое имя потока: " + mainThread.name)
        Log.d(MainActivity::class.java.simpleName,"Stack:" + Arrays.toString(mainThread.stackTrace))

        Log.d(MainActivity::class.java.simpleName, "Group:	" + mainThread.threadGroup)


        binding.buttonMirea.setOnClickListener {
            Thread {
                val numberThread = counter++
                Log.d(
                    "ThreadProject",
                    "Запущен поток № $numberThread студентом группы № БСБО-10-21 номер по списку № 6"
                )
                val endTime = System.currentTimeMillis() + 20*1000
                while (System.currentTimeMillis() < endTime) {
                    synchronized(this) {
                        try {
                            Thread.sleep(endTime - System.currentTimeMillis())
                        } catch (e: Exception) {
                            throw RuntimeException(e)
                        }
                    }
                    Log.d("ThreadProject", "Выполнен поток № $numberThread")
                }
            }.start()
        }

        binding.getLessons.setOnClickListener {
            Thread {
                var days: Int = 1
                var lessons: Float = 0f
                try {
                    days = binding.days.text.toString().toInt()
                    lessons = binding.lessons.text.toString().toFloat()
                    if (days == 0)
                        return@Thread
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }

                val result: Float = lessons/days
                binding.textView2.text = "Среднее количество пар в день: $result"
            }.start()
        }
    }
}