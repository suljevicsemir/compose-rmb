package com.semirsuljevic.raiffaisenmobileapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.*

class RMBFirebaseMessagingService: FirebaseMessagingService() {


    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "RideArrival"
            val descriptionText = "Channel for Ride Arriving"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("RideArrival", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("NEW_TOKEN_GENERATED", p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val now = Date();
        val notificationId: Int = SimpleDateFormat("ddHHmmss", Locale.US).format(now).toInt();
        val builder = NotificationCompat.Builder(this, "RideArrival")
            .setSmallIcon(R.drawable.ic_youtube)
            .setColor(Color.BLACK)
            .setContentTitle("Hot milfs in your area")
            .setContentText("You ready?")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setContentIntent(PendingIntent.getActivity(this, 0, Intent(), 0))

        vibrate()

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
        Log.d("REMOTE_MESSAGE_RECEIVED", p0.notification?.body.toString())
    }

    private fun vibrate() {

        val vibrator  = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =  this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator;
        } else {
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(2000, 255), AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build())
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(2000);

        }
    }
}