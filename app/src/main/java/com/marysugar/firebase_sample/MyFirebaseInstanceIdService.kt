package com.marysugar.firebase_sample

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseInstanceIdService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("TAG", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d("TAG", "sendRegistrationTokenToServer($token)")
    }

    fun getNowToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("AAA",token.toString())

//            // Log and toast
//            val msg = getString(R.string.msg_token_fmt, token)
//            Log.d("TAG", msg)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }
}