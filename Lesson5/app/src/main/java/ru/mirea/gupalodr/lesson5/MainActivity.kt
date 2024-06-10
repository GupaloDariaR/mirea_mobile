package ru.mirea.gupalodr.lesson5

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import ru.mirea.gupalodr.lesson5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val listSensor = binding.listView

        // создаем список для отображения в ListView найденных датчиков
        val arrayList = ArrayList<HashMap<String, Any>>()

        for (i in 0..< sensors.size) {
            val sensorTypeList = HashMap<String, Any>()
            sensorTypeList["Name"] = sensors[i].name
            sensorTypeList["Value"] = sensors[i].maximumRange
            arrayList.add(sensorTypeList)
        }

        // создаем адаптер и устанавливаем тип адаптера - отображение двух полей
        val mHistory = SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2,
            arrayOf("Name", "Value"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        listSensor.adapter =  mHistory
    }
}