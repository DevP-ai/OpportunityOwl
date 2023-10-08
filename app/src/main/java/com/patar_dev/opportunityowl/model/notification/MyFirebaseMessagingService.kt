package com.patar_dev.opportunityowl.model.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.activity.DescriptionActivity
import com.patar_dev.opportunityowl.activity.MainActivity
import kotlinx.coroutines.MainScope
import kotlin.random.Random

class MyFirebaseMessagingService: FirebaseMessagingService() {
    private val channelId = "viraj"

    override fun onNewToken(token: String) {
        val tokenSharedPreferences = getSharedPreferences("NewToken", MODE_PRIVATE)
        tokenSharedPreferences.edit().apply {
            putString("newToken",token)
            apply()
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val intent = Intent(this, DescriptionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val manager = getSystemService(Context.NOTIFICATION_SERVICE)
        createNotificationChannel(manager as NotificationManager)
        val contentText = message.data["companyName"] + " hiring for " + message.data["jobTitle"] + "(" + message.data["location"] + ")"
        val intent1 = PendingIntent.getActivities(this,0, arrayOf(intent), PendingIntent.FLAG_IMMUTABLE)
        val largeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.bag)
        val notification = NotificationCompat.Builder(this,channelId)
            .setContentTitle(message.data["companyName"])
            .setContentText(contentText)
            .setSmallIcon(R.drawable.bag)
            .setAutoCancel(true)
            .setLargeIcon(largeIcon)
            .setContentIntent(intent1)
            .build()
        manager.notify(Random.nextInt(),notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            channelId, "virajchat",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "New Chat"
        channel.enableLights(true)
        notificationManager.createNotificationChannel(channel)
    }
}