package com.example.app1

import androidx.appcompat.app.AppCompatDelegate
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonSubmit)
        val textView = findViewById<TextView>(R.id.textViewResult)
        val changeThemeSwitch = findViewById<Switch>(R.id.changeThemeSwitch)
        val cambiarInterfaz = findViewById<Button>(R.id.changeInterface)

        changeThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        button.setOnClickListener {
            textView.text = "Hola Javi"
        }

        cambiarInterfaz.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}

//1.tocar un boton y que se escriba un mensaje en el textview
//2. 2 inputs, un boton que sume y muestre el resultado
//3. nombre de usuario, contraseña y boton que valide con un string harcodeado. Cada vez que se erre lanzara un aviso, al tercer aviso
//bloqueran los inputs.

// se puede hacer tanto en el mismo proyecto como en separados