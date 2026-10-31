package com.example.app1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    private val usuarioCorrecto = "pepito"
    private val passwordCorrecto = "pepito"
    private var intentosRestantes = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        val validar = findViewById<Button>(R.id.validate)
        val usuario = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val resultado = findViewById<TextView>(R.id.estado)
        val changeThemeSwitch = findViewById<Switch>(R.id.changeThemeSwitch3)
        val cambiarInterfaz = findViewById<Button>(R.id.changeInterface3)

        cambiarInterfaz.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        changeThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        validar.setOnClickListener {
            val userIngresado = usuario.text.toString()
            val passIngresada = password.text.toString()

            if (userIngresado == usuarioCorrecto && passIngresada == passwordCorrecto) {
                resultado.text = "Se accedió a la cuenta correctamente"
                resultado.setTextColor(Color.GREEN)
            } else {
                intentosRestantes--
                if (intentosRestantes > 0) {
                    resultado.text = "Error al iniciar sesión, quedan $intentosRestantes intentos"
                    resultado.setTextColor(Color.RED)
                } else {
                    resultado.text = "Demasiados intentos fallidos, sistema bloqueado."
                    resultado.setTextColor(Color.RED)

                    usuario.isEnabled = false
                    password.isEnabled = false
                    validar.isEnabled = false
                }

            }
        }
    }
}
