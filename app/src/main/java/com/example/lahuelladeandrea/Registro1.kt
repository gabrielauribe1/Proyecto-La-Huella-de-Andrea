package com.example.lahuelladeandrea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lahuelladeandrea.database.AppDatabase
import com.example.lahuelladeandrea.database.UserModel
import kotlinx.coroutines.*

class Registro1 : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerButton: Button
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        database = AppDatabase.getDatabase( this )
        firstNameInput = findViewById(R.id.registerFirstNameInput)
        lastNameInput = findViewById(R.id.registerLastNameInput)
        emailInput = findViewById(R.id.registerEmailnput)
        passwordInput = findViewById(R.id.registerPasswordInput)
        registerButton = findViewById(R.id.registro2)

        registerButton.setOnClickListener {
            val firstName = firstNameInput.text.toString()
            val lastName = lastNameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if( firstName.isEmpty()) {
                showToast("Ingrese nombre")
                return@setOnClickListener
            }

            if( lastName.isEmpty()) {
                showToast("Ingrese apellido")
                return@setOnClickListener
            }

            if( email.isEmpty()) {
                showToast("Ingrese correo")
                return@setOnClickListener
            }

            if( password.isEmpty() || password.length < 8) {
                showToast("Ingrese contraseña mayor a 7 caracteres")
                return@setOnClickListener
            }


            userModel = UserModel(false, firstName, lastName, email, password)
            CoroutineScope(Dispatchers.IO).launch {
                val result = withContext(Dispatchers.Default) {
                    database.users().validationUser(email)
                }

                if( result == null) {
                    database.users().insertUser(userModel)
                    goToHomeActivity()
                } else {
                    runOnUiThread {
                        showToast("Error, cuenta ya existe")
                    }
                }

            }

        }

        settingsActionBar()
    }

    private fun goToHomeActivity() {
        val intent = Intent(this,HomePage1::class.java)
        intent.putExtra("IS_ADMIN", userModel.isAdmin )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    // Configura el actionBar con un nuevo título y la posibilidad para poder de regresar al Main Activity.
    private fun settingsActionBar() {
        val actionBar = supportActionBar
        actionBar?.title = "Registro"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Habilita la opción de poder regresar al MainActivity.
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}