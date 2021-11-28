package com.example.lahuelladeandrea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Nosotros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nosotros)
        settingsActionBar()
    }

    // Configura el actionBar con un nuevo título y la posibilidad para poder de regresar al Main Activity.
    private fun settingsActionBar() {
        val actionBar = supportActionBar
        actionBar?.title = "Nosotras"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Habilita la opción de poder regresar al MainActivity.
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}