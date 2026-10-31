package com.example.app1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.content.Intent
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        val button = findViewById<Button>(R.id.sum)
        val respuesta = findViewById<TextView>(R.id.answer)
        val numero1 = findViewById<EditText>(R.id.number1)
        val numero2 = findViewById<EditText>(R.id.number2)
        val cambiarInterfaz = findViewById<Button>(R.id.changeInterface2)
        val changeThemeSwitch = findViewById<Switch>(R.id.changeThemeSwitch2)

        changeThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        button.setOnClickListener {
            val valor1 = numero1.text.toString().toIntOrNull() ?: 0
            val valor2 = numero2.text.toString().toIntOrNull() ?: 0

            val suma = valor1 + valor2

            respuesta.text = suma.toString()
        }

        cambiarInterfaz.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

    }
}
