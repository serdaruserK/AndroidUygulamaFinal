package com.example.newspapers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyReceiver : BroadcastReceiver() {
    private val CHANNEL_ID = "com.example.newspapers"
    private  var notificationId = 1111

    override fun onReceive(context: Context?, intent: Intent?) {

        createNotificationChannel(context)
        val action = intent?.action

        if(action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
            if (intent!!.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {

            }else {
                sendNotification(context)
                notificationId ++
            }
        }

    }

    private fun createNotificationChannel(context: Context?) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "name"
            val descriptionText = "desccription"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance) .apply {
                description = descriptionText
            }
            val notificationManager : NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(context: Context?) {
        val builder = NotificationCompat.Builder(context!!, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Wifi Bağlantı Hatası!")
            .setContentText("Wifi bağlantısı kesildi. Wifi ağına bağlandıktan sonra devam edebilirsiniz..")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }
}