package com.example.app1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity4 : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private var luz: Sensor? = null
    private var proximidad: Sensor? = null

    private lateinit var txtSensor1: TextView
    private lateinit var txtSensor2: TextView
    private lateinit var txtSensor3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtSensor1 = findViewById(R.id.txtSensor1)
        txtSensor2 = findViewById(R.id.txtSensor2)
        txtSensor3 = findViewById(R.id.txtSensor3)
        val cambiarInterfaz = findViewById<Button>(R.id.changeInterfaceto1)

        cambiarInterfaz.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        proximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onResume() {
        super.onResume()
        // Registrar los eventos de los sensores
        acelerometro?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
        luz?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
        proximidad?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }

    override fun onPause() {
        super.onPause()
        // Pausar la escucha de sensores para ahorrar batería cuando la app no está activa
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                txtSensor1.text = "Acelerómetro:\nX: $x\nY: $y\nZ: $z"
            }
            Sensor.TYPE_LIGHT -> {
                val luzValor = event.values[0]
                txtSensor2.text = "Luz ambient: $luzValor lx"
            }
            Sensor.TYPE_PROXIMITY -> {
                val proximidadValor = event.values[0]
                txtSensor3.text = "Proximidad: $proximidadValor cm"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Sin uso por ahora, pero obligatorio implementarlo por la interfaz SensorEventListener
    }
}