package com.example.chroniccaremanagementapp

import DatabaseHelper
import android.app.*
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.edit_note.*
import kotlinx.android.synthetic.main.posts_single.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class PostsAdapter(var context: Context) : BaseAdapter() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    val dbObj = DatabaseHelper(context)
    val ss = dbObj.allNotes()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var abc = LayoutInflater.from(context).inflate(R.layout.posts_single, p2, false)
        abc.heading1.text = ss[p0].title.toString()
        abc.heading2.text =ss[p0].subTitle.toString()
        abc.description.text = ss[p0].desc.toString()
        abc.posttime.text = ss[p0].modTime.toString()
        abc.reminder.text = ss[p0].reminderTime.toString()
        abc.deleteButton.setOnClickListener {
            dbObj.deleteData(ss[p0])
            ss.remove(ss[p0])
            notifyDataSetChanged()
            Toast.makeText(context, "${p0}", Toast.LENGTH_LONG).show()
        }
        abc.editButton.setOnClickListener {
            val epicDialog = Dialog(context)
            epicDialog.setContentView(R.layout.edit_note)
            epicDialog.editNoteTitle.text = "Edit Note"
            epicDialog.titleEdit.setText(ss[p0].title.toString())
            epicDialog.descriptionEdit.setText(ss[p0].desc.toString())
            epicDialog.subTitleEdit.setText(ss[p0].subTitle.toString())
            epicDialog.reminderSwitch.isChecked = ss[p0].isReminder==1
            val alarmCalendar = Calendar.getInstance()
            epicDialog.postTimePicker.setOnTimeChangedListener {
                    timePicker, i, i2 ->
                val year = alarmCalendar.get(Calendar.YEAR)
                val month = alarmCalendar.get(Calendar.MONTH)
                val day = alarmCalendar.get(Calendar.DATE)
                alarmCalendar.set(year, month, day, i, i2, 0)
            }
            val currentDateTime = LocalDateTime.now()
            val currentTime =currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)).toString() + " "+currentDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)).toString()
            val ok = epicDialog.findViewById<TextView>(R.id.postOK)
            ok.setOnClickListener {
                if (epicDialog.titleEdit.text!!.isEmpty() || epicDialog.subTitleEdit.text!!.isEmpty() || epicDialog.descriptionEdit.text!!.isEmpty()) {
                    Toast.makeText(context, "Please add all details", Toast.LENGTH_LONG).show()
                }
                else {
                    var some = 0
                    if (epicDialog.reminderSwitch.isChecked){
                        some = 1
                    }
                    var abc = Notes( ss[p0].noteId, "${epicDialog.titleEdit.text}", "${epicDialog.subTitleEdit.text}", "${epicDialog.descriptionEdit.text}", "${currentTime}", some, alarmCalendar.timeInMillis )
                    dbObj.updateData(abc)
                    epicDialog.dismiss()
                    Toast.makeText(context, "${epicDialog.titleEdit.text}", Toast.LENGTH_LONG)
                        .show()
                    notifyDataSetChanged()
                }
            }
            epicDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            epicDialog.window!!.setLayout( ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT )
            epicDialog.show()
        }
        return abc
    }

    override fun getItem(p0: Int): Any {
        return ss.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return ss.size
    }
}