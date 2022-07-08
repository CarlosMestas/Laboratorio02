package com.aangles.cmestas.myquispeyn.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.app.NotificationCompat
import com.aangles.cmestas.myquispeyn.MainActivity
import com.aangles.cmestas.myquispeyn.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService:FirebaseMessagingService() {

    companion object {
        private const val TAG = "FCM Notification"
        const val DEFAULT_NOTIFICATION_ID = 0
    }

    override fun onNewToken(token: String) {
        Log.i(TAG, "new FCM token created: $token")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }

    @OptIn(ExperimentalMaterialApi::class)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body
        //Para personalizar la notificación
        val notificationLayout = RemoteViews(packageName, R.layout.textview)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.textviewexpanded)
        //Activity
        val intentNotif = Intent(this, MainActivity::class.java)
        intentNotif.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendIntent = PendingIntent.getActivity(this, 0, intentNotif, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notificationBuilder = if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            NotificationCompat.Builder(applicationContext, "1")
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        notificationBuilder = notificationBuilder
            .setSmallIcon(R.drawable.ic_parking_svgrepo_new)
            .setContentTitle("Parqueo Nuevo")
            .setTicker(getText(R.string.app_name))
            .setContentText("Revisa si te interesa")
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setCustomBigContentView(notificationLayoutExpanded)
            .setContentIntent(pendIntent)


        initNotificationChannel(notificationManager)
        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun initNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            notificationManager.createNotificationChannelIfNOtExists(
                channelId = "1",
                channelName = "Default"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createNotificationChannelIfNOtExists(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    var channel = this.getNotificationChannel(channelId)

    if (channel == null) {
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}






