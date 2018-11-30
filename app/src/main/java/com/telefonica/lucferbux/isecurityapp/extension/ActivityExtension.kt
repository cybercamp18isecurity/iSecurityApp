package com.telefonica.lucferbux.isecurityapp.extension

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.widget.Toast
import com.pusher.pushnotifications.PushNotifications

fun waitAndDo(millis: Long, action: () -> Unit) {
    Handler().postDelayed(action, millis)
}

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


private fun Activity.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


fun Activity.setPushNotification() {
    PushNotifications.start(this.getApplicationContext(), "c5ba8cdb-bf9a-49c4-881c-a14bacba5927");
    PushNotifications.subscribe("hello");
}