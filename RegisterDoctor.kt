package com.example.chroniccaremanagementapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterDoctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_doctor)

        val fullname:EditText = findViewById(R.id.fullname)
        val email:EditText = findViewById(R.id.email)
        val uname:EditText = findViewById(R.id.uname)
        val pass:EditText = findViewById(R.id.pass)
        val mobno:EditText = findViewById(R.id.mobno)
        val location:EditText = findViewById(R.id.location)
        val register2:Button = findViewById(R.id.register2)
        val progress: ProgressBar = findViewById(R.id.progress_horizontal)

        val mAuth:FirebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        register2.setOnClickListener(View.OnClickListener {
            val fullname1 = fullname.text.toString()
            val email1 = email.text.toString()
            val uname1 = uname.text.toString()
            val pass1 = pass.text.toString()
            val mobno1 = mobno.text.toString()
            val location1 = location.text.toString()

            if (pass.length() < 8) {
                pass.setError("Password must be greater than 8 characters long.")
            }
            if (mobno.length() < 10 || mobno.length() > 10) {
                mobno.setError("Mobile Number must be 10 digits only.")
            }

            val user: FirebaseUser? = mAuth.getCurrentUser()
            if (user != null) {
                Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegisterDoctor, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                mAuth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(this, OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Congrats User Created!", Toast.LENGTH_SHORT).show()
                        val user1:HashMap<String, String> = HashMap<String, String>()
                        user1.put("fName", fullname1)
                        user1.put("email", email1)
                        user1.put("uname", uname1)
                        user1.put("password", pass1)
                        user1.put("mobno", mobno1)
                        user1.put("location", location1)
                        db.collection("doctor_registration").document("${email1}")
                                .set(user1)
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener{
                                    Toast.makeText(this,"Failure", Toast.LENGTH_LONG).show()
                                }
                        progress.setVisibility(View.VISIBLE)
                        Toast.makeText(this, "Redirecting you to Home Page!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterDoctor, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this, "User Exist", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }
}