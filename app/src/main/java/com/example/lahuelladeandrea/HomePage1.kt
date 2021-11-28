package com.example.lahuelladeandrea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomePage1 : AppCompatActivity() {
    private var isAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        isAdmin = intent.getBooleanExtra("IS_ADMIN", false)
    }

    /*Funcion que despliega la actividad Q&A, el layout cambia dependiendo si es usuario o administrador*/
    fun redirectoToQA(boton: View) {
        var intent = Intent(this,QandA::class.java)
        if( isAdmin ) {
            intent = Intent(this,QandAAdmin::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
    }

    /*Funcion que despliega la actividad Motivacion, el layout cambia dependiendo si es usuario o administrador*/
    fun redirectToMotivacion(boton: View) {
        var intent = Intent(this,Motivacion1::class.java)
        if( isAdmin ){
            intent = Intent(this,MotivacionAdmin::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
    }

    /*Funcion que despliega la actividad Blog, el layout cambia dependiendo si es usuario o administrador*/
    fun redirectToBlog(boton: View) {
        var intent = Intent(this,Blog1::class.java)
        if( isAdmin ){
            intent = Intent(this,BlogAdmin::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
    }

    /*Funcion que despliega la actividad Ayuda*/
    fun redirectToAyuda(boton: View) {
        var intent = Intent(this,Ayuda::class.java)
        if( isAdmin ){
            intent = Intent(this,Ayuda::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
    }

    /*Funcion que despliega la actividad Nosotros*/
    fun redirectToNosotros(boton: View) {
        var intent = Intent(this,Nosotros::class.java)
        if( isAdmin ){
            intent = Intent(this,Nosotros::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
    }
}