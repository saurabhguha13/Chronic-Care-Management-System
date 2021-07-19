package com.example.chroniccaremanagementapp

import DatabaseHelper
import android.app.ActionBar
import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.edit_note.*

class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        var listItems = ArrayList<Notes>()
        val dbObj = DatabaseHelper(this)

        floatingPosts.setOnClickListener {
            val epicDialog = Dialog(this)
            epicDialog.setContentView(R.layout.edit_note)
            val closeImage = epicDialog.findViewById<Button>(R.id.closeImage)
            var reminderTime: String = ""
            val alarmCalendar = Calendar.getInstance()
            epicDialog.postTimePicker.setOnTimeChangedListener { timePicker, i, i2 ->
                val year = alarmCalendar.get(Calendar.YEAR)
                val month = alarmCalendar.get(Calendar.MONTH)
                val day = alarmCalendar.get(Calendar.DATE)
                alarmCalendar.set(year, month, day, i, i2, 0)
                reminderTime = "Reminder at " + SimpleDateFormat("MMM, dd YYYY hh:mm:ss a").format(alarmCalendar.time)
            }
            val deleteButton = epicDialog.findViewById<ImageView>(R.id.deleteButton)
            var currentTime: String = ""
            val alarmCalendar2 = Calendar.getInstance()
            val year = alarmCalendar2.get(Calendar.YEAR)
            val month = alarmCalendar2.get(Calendar.MONTH)
            val day = alarmCalendar2.get(Calendar.DATE)
            val time = alarmCalendar2.get(Calendar.HOUR)
            val minute = alarmCalendar2.get(Calendar.MINUTE)
            val secs = alarmCalendar2.get(Calendar.SECOND)
            alarmCalendar2.set(year, month, day, time, minute, secs)
            val currentDateTime = LocalDateTime.now()
            currentTime =currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)).toString() + " "+currentDateTime.format(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)).toString()
            val ok = epicDialog.findViewById<TextView>(R.id.postOK)
            ok.setOnClickListener {
                if(epicDialog.titleEdit.text!!.isEmpty() || epicDialog.subTitleEdit.text!!.isEmpty() || epicDialog.descriptionEdit.text!!.isEmpty()){
                    Toast.makeText(this, "Please add all details", Toast.LENGTH_LONG).show()
                }
                else{
                    val ab1 = dbObj.allNotes()
                    val needToStore = ab1.size + 1
                    var some=0
                    if(epicDialog.reminderSwitch.isChecked){
                        some = 1
                    }
                    var abc = Notes( needToStore, "${epicDialog.titleEdit.text}", "${epicDialog.subTitleEdit.text}", "${epicDialog.descriptionEdit.text}", "${currentTime}", some, alarmCalendar.timeInMillis )
                    val dbObj = DatabaseHelper(this)
                    dbObj.insertData(abc)
                    val ss = dbObj.allNotes()
                    for (a in ss){
                        Log.d("PostActivity", a.title.toString())
                    }
                    //listsPosts.adapter = PostsAdapter(this)
                    Toast.makeText(this, "NOTIF: ${alarmCalendar.timeInMillis}", Toast.LENGTH_LONG).show()
                    sendNotification(alarmCalendar.timeInMillis, needToStore)
                    val NOTIF_CHANNEL_ID = "heyyy"
                    listItems.add(abc)
                    Notes.generateID()
                    epicDialog.dismiss()
                    Toast.makeText(this, "${epicDialog.titleEdit.text}", Toast.LENGTH_LONG).show()
                  //  loadData()
                }
            }
            closeImage.setOnClickListener {
                epicDialog.dismiss()
            }
            epicDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            epicDialog.window!!.setLayout( ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT )
            epicDialog.show()
        }
    }

    fun setAlarm(millisTime: Long, str: String) {
        val intent = Intent(this, MyReceiver::class.java)
        intent.putExtra("Service1", str)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 234324243, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (str == "Start") {
            Toast.makeText(this, "setAlarmCalled", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "${millisTime}", Toast.LENGTH_LONG).show()
            alarmManager.setExact( AlarmManager.RTC_WAKEUP, millisTime, pendingIntent )
        }
        else if (str == "Stop") {
            alarmManager.cancel(pendingIntent)
            sendBroadcast(intent)
            Toast.makeText(this, "Alarm Stopped", Toast.LENGTH_LONG).show()
        }
    }

    fun sendNotification(timeInMillis: Long, id: Int){
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(applicationContext, MyReceiver::class.java)
        alarmIntent.putExtra("timestamp", timeInMillis)
        alarmIntent.putExtra("noteId", id)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
}