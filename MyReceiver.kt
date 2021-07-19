package com.example.chroniccaremanagementapp

import DatabaseHelper
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.util.*

class MyReceiver : BroadcastReceiver() {
    var mp: MediaPlayer? = null
    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000
    val NOTIF_CHANNEL_ID1 = "NOTIFID"
    lateinit var dbObj: DatabaseHelper

    override fun onReceive(context: Context, intent: Intent) {
        dbObj = DatabaseHelper(context)
        if (intent != null && context != null) {
            val str1 = intent.getStringExtra("Service1")
            val str2 = intent.getLongExtra("timestamp", -1)
            val str3 = intent.getIntExtra("noteId", -1)
            if (str1 == null) {

            }
            else if (str1 == "Start" || str1 == "Stop") {
                val intentService = Intent(context, AlarmService::class.java)
                intentService.putExtra("Service1", intent.getStringExtra("Service1"))
                if (str1 == "Start")
                    context.startService(intentService)
                else if (str1 == "Stop")
                    context.stopService(intentService)
            }
            val ss = dbObj.allNotes()
            Log.d("Helper3", str3.toString())
            for (a in ss) {
                Log.d("HELPER 4", a.noteId.toString())
            }
            var abc34 = ss.filter {
                    s -> s.noteId == str3
            }
            Log.d("HELPER1", abc34[0].toString())
            if (str2 != null) {
                Toast.makeText(context, "YOUR RECV", Toast.LENGTH_LONG).show()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Toast.makeText(context, "Not ID: ${str3}", Toast.LENGTH_LONG).show()
                    val context = context.applicationContext
                    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val importance = NotificationManager.IMPORTANCE_HIGH
                    val notificationChannel = NotificationChannel(NOTIF_CHANNEL_ID1, "Something", importance)
                    notificationChannel.enableVibration(true)
                    notificationChannel.setShowBadge(true)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.parseColor("#e8334a")
                    notificationChannel.description = "SOMETHING"
                    notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                    notificationManager.createNotificationChannel(notificationChannel)
                }
                var timestamp = intent.extras!!.getLong("timestamp")
                val context = context.applicationContext
                var notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notifyIntent = Intent(context, Diagnostics::class.java)
                val title = abc34[0].title
                val message = abc34[0].desc
                notifyIntent.putExtra("noteId", str3)
                notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = timestamp
                val pendingIntent = PendingIntent.getActivity( context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT )
                val res = context.resources
                mNotification = Notification.Builder(context, NOTIF_CHANNEL_ID1)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setTicker("Something")
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .build()
                notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(mNotificationId, mNotification)
            }
        }
    }
}