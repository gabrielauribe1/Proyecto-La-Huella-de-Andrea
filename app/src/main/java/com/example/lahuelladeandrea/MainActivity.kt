package com.example.lahuelladeandrea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        /*Se dirige al layout de registro*/
        val registroppal = findViewById<Button>(R.id.registro)
        registroppal.setOnClickListener{
            val intent = Intent(this, Registro1::class.java)
            startActivity(intent)
        }

        /*Se dirige al layout de login*/
        val loginppal = findViewById<Button>(R.id.login)
        loginppal.setOnClickListener{
            val intent = Intent(this, Login1::class.java)
            startActivity(intent)
        }
    }
}