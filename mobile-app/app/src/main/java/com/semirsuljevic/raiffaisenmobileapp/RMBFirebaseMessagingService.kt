package com.semirsuljevic.raiffaisenmobileapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class RMBFirebaseMessagingService: FirebaseMessagingService() {


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("NEW_TOKEN_GENERATED", p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("REMOTE_MESSAGE_RECEIVED", p0.notification?.body.toString())
    }
}