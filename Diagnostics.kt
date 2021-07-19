package com.example.chroniccaremanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class Diagnostics : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostics)
        val heading1 = findViewById<TextView>(R.id.heading1)
        val heading2 = findViewById<TextView>(R.id.heading2)
        val description = findViewById<TextView>(R.id.description)
        val posttime = findViewById<TextView>(R.id.posttime)
        val reminder = findViewById<TextView>(R.id.reminder)

        var id = intent.getIntExtra("noteId", -1)
        var abc34 = Notes.noteArray.filter { s -> s.noteId==id }
        heading1.text = abc34[0].title
        description.text = abc34[0].desc
        heading2.text = abc34[0].subTitle
        val formatter = SimpleDateFormat("MMM, dd YYYY hh:mm:ss a")
        val calc = Calendar.getInstance()
        calc.timeInMillis = abc34[0].reminderTime
        reminder.text = formatter.format(calc.time)
        posttime.text = abc34[0].modTime
    }
}