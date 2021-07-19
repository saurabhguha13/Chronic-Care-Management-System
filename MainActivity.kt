package com.example.chroniccaremanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val patlogin = findViewById<Button>(R.id.patlogin)
        val doclogin = findViewById<Button>(R.id.doclogin)
        val register = findViewById<TextView>(R.id.register)

        patlogin.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@MainActivity, LoginPatient::class.java)
            startActivity(intent)
            finish()
        })

        doclogin.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@MainActivity, LoginDoctor::class.java)
            startActivity(intent)
            finish()
        })

        register.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@MainActivity, RegisterationPage::class.java)
            startActivity(intent)
            finish()
        })
    }
}