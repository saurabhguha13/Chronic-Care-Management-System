package com.example.chroniccaremanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginDoctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_doctor)

        val email: EditText = findViewById(R.id.email)
        val docpass: EditText = findViewById(R.id.docpass)
        val doclogin: Button = findViewById(R.id.doclogin)
        val register2 = findViewById<TextView>(R.id.register2)

        val progress: ProgressBar = findViewById(R.id.progress_horizontal1)
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        doclogin.setOnClickListener(View.OnClickListener {
            val email1 = email.text.toString()
            val docpass1 = docpass.text.toString()

            if (TextUtils.isEmpty(email1)) {
                email.setError("Email is required!")
            }
            if (TextUtils.isEmpty(docpass1)) {
                docpass.setError("Password is required!")
            }
            if (docpass.length() < 8) {
                docpass.setError("Password must be greater than 8 characters long.")
            }

            register2.setOnClickListener(View.OnClickListener {
                val intent: Intent = Intent(this@LoginDoctor, RegisterDoctor::class.java)
                startActivity(intent)
                finish()
            })
            progress.setVisibility(View.VISIBLE)

            mAuth.signInWithEmailAndPassword(email1, docpass1).addOnCompleteListener(this, OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginDoctor, "You are signed in successfully!", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this@LoginDoctor, DoctorFacilities::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this@LoginDoctor, "Email or password incorrect", Toast.LENGTH_SHORT).show()
                    progress.setVisibility(View.GONE)
                    val intent: Intent = Intent(this@LoginDoctor, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        })
    }
}