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

class LoginPatient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_patient)

        val email: EditText = findViewById(R.id.patient_email)
        val pass: EditText = findViewById(R.id.patient_pwd)
        val login: Button = findViewById(R.id.login)
        val register1 = findViewById<TextView>(R.id.register1)

        val progress: ProgressBar = findViewById(R.id.progress_horizontal)
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        login.setOnClickListener(View.OnClickListener {
            val email1 = email.text.toString()
            val pass1 = pass.text.toString()

            if (TextUtils.isEmpty(email1)) {
                email.setError("Email is required!")
            }
            if (TextUtils.isEmpty(pass1)) {
                pass.setError("Password is required!")
            }
            if (pass.length() < 8) {
                pass.setError("Password must be greater than 8 characters long.")
            }

            register1.setOnClickListener(View.OnClickListener {
                val intent: Intent = Intent(this@LoginPatient, RegisterPatient::class.java)
                startActivity(intent)
                finish()
            })
            progress.setVisibility(View.VISIBLE)

            mAuth.signInWithEmailAndPassword(email1, pass1).addOnCompleteListener(this, OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginPatient, "You are signed in successfully!", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this@LoginPatient, PatientFacilities::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this@LoginPatient, "Email or password incorrect", Toast.LENGTH_SHORT).show()
                    progress.setVisibility(View.GONE)
                    val intent: Intent = Intent(this@LoginPatient, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        })
    }
}