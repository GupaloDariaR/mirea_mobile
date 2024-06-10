package ru.mirea.gupalodr.accelerometer

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.gupalodr.accelerometer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager : SensorManager
    private lateinit var accelerometerSensor : Sensor
    private lateinit var azimuthTextView: TextView
    private lateinit var  pitchTextView: TextView
    private lateinit var  rollTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!

        azimuthTextView = binding.textViewAzimuth
        pitchTextView = binding.textViewPitch
        rollTextView = binding.textViewRoll
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val valueAzimuth = event.values[0]
                val valuePitch = event.values[1]
                val valueRoll = event.values[2]

                azimuthTextView.text = "Azimuth: $valueAzimuth"
                pitchTextView.text = "Pitch: $valuePitch"
                rollTextView.text = "Roll: $valueRoll"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}