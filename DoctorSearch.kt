package com.example.chroniccaremanagementapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class DoctorSearch : AppCompatActivity() {
    lateinit var lv1: ListView
    var mTitle = arrayOf("Dr. M.L. Hindonia", "Dr. Tosher S. Contactor", "Dr. Vishal Sanghi", "Dr. S.R. Bansal",
            "Dr. Rajkumar Mewara", "Dr. Hardik Kumar", "Dr. Manoj Jain", "Dr. Lal Patlab",
            "Dr. Goyal", "Dr. Sanjeevni", "Dr. Jeevanjyot", "Dr. Balaji",
            "Dr. J. Wathumull", "Dr. Radha Mohan", "Dr. Abhinav Bal", "Dr. Kishore Kumar",
            "Dr. Seema Laad", "Dr. Radhe Shyamtiwari", "Dr. Nikhil Patel", "Dr. Hargovindkela")
    var mDescription = arrayOf("Phone Number: 9874563210", "Phone Number: 9632587410", "Phone Number: 9512357886", "Phone Number: 9123456780",
            "Phone Number: 9321456780", "Phone Number: 9654781230", "Phone Number: 9660432551", "Phone Number: 9887464121",
            "Phone Number: 9783188251", "Phone Number: 9414175232", "Phone Number: 9456123780", "Phone Number: 9786541230",
            "Phone Number: 9258741630", "Phone Number: 9874635210", "Phone Number: 9513642870", "Phone Number: 9368257140",
            "Phone Number: 9741256380", "Phone Number: 9321456870", "Phone Number: 7856941230", "Phone Number: 7568941230")
    var images = intArrayOf(R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4,
            R.drawable.d5, R.drawable.d6, R.drawable.d7, R.drawable.d8,
            R.drawable.d9, R.drawable.d10, R.drawable.d11, R.drawable.d12,
            R.drawable.d13, R.drawable.d14, R.drawable.d15, R.drawable.d16,
            R.drawable.d17, R.drawable.d18, R.drawable.d19, R.drawable.d20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_search)
        lv1 = findViewById(R.id.lv1)
        ProgramAdapter(this@DoctorSearch, mTitle, mDescription, images)
    }
}