package com.example.chroniccaremanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class PatientFacilities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_facilities)

        val diagnostics = findViewById<ImageButton>(R.id.diagnostics)
        diagnostics.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@PatientFacilities, PostsActivity::class.java)
            startActivity(intent)
            finish()
        })

        val doctorsearch = findViewById<ImageButton>(R.id.doctorsearch)
        doctorsearch.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@PatientFacilities, DoctorSearch::class.java)
            startActivity(intent)
            finish()
        })

        val hospitalsearch = findViewById<ImageButton>(R.id.hospitalsearch)
        hospitalsearch.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@PatientFacilities, HospitalSearch::class.java)
            startActivity(intent)
            finish()
        })

        val currentLocation = findViewById<ImageButton>(R.id.currentlocation)
        currentLocation.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@PatientFacilities, PatientCurrentLocation::class.java)
            startActivity(intent)
            finish()
        })

        val pharmacy = findViewById<ImageButton>(R.id.pharmacy)
        pharmacy.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@PatientFacilities, PharmacySearch::class.java)
            startActivity(intent)
            finish()
        })
    }
}