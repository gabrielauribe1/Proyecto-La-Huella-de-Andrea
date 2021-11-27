package com.example.lahuelladeandrea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomePage1 : AppCompatActivity() {
    private var isAdmin: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        isAdmin = intent.getStringExtra("IS_ADMIN").toString()
    }

    fun redirectoToQA() {
        var intent = Intent(this,QandA::class.java)
        if(isAdmin.toBoolean()){
            intent = Intent(this,QandAAdmin::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
    }
}