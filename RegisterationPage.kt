package com.example.chroniccaremanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class RegisterationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration_page)

        val patreg = findViewById<Button>(R.id.patreg)
        val docreg = findViewById<Button>(R.id.docreg)

        patreg.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@RegisterationPage, RegisterPatient::class.java)
            startActivity(intent)
            finish()
        })
        docreg.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@RegisterationPage, RegisterDoctor::class.java)
            startActivity(intent)
            finish()
        })
    }
}