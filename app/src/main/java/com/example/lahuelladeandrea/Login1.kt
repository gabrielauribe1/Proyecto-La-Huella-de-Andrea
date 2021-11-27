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

class Login1 : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private var user:UserModel? = null
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var currentUser: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        settingsActionBar()

        database = AppDatabase.getDatabase(this)

        val loginhomedirect = findViewById<Button>(R.id.login2)
        emailInput = findViewById(R.id.loginEmailInput)
        passwordInput = findViewById(R.id.loginPasswordInput)

        loginhomedirect.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            // Acción asíncrona (corrutina) para hacer un registro en la BD

            CoroutineScope(Dispatchers.IO).launch {
                val result = async {
                    val usuario= database.users().login(email, password)
                    currentUser = usuario!!
                }

                if (result.await() != null) {

                    goToHomeActivity(currentUser.isAdmin.toString())
                } else {
                    runOnUiThread {
                        showErrorLoginToast()
                    }
                }
            }
            println("User in DB: $user")
        }
    }

    private fun goToHomeActivity(is_admin: String) {
        val intent = Intent(this,HomePage1::class.java)
        intent.putExtra("IS_ADMIN", is_admin)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }

    private fun showErrorLoginToast() {
        Toast.makeText(this, "Usuario no encontrado, regístrate por favor", Toast.LENGTH_SHORT).show()
    }
    // Configura el actionBar con un nuevo título y la posibilidad para poder de regresar al Main Activity.
    private fun settingsActionBar() {
        val actionBar = supportActionBar
        actionBar?.title = "Login"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Habilita la opción de poder regresar al MainActivity.
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}