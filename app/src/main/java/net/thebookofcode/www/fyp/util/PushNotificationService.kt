package net.thebookofcode.www.fyp.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import net.thebookofcode.www.fyp.R
import net.thebookofcode.www.fyp.repository.MainRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService() {
    @Inject
    lateinit var repository:MainRepository

    @SuppressLint("MissingPermission")
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification!!.title
        val text = message.notification!!.body
        val CHANNEL_ID = "INTRUDER_ALERT"

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Intruder Alert",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.createNotificationChannel(channel)
        val notification = android.app.Notification.Builder(applicationContext,CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_place_holder)
            .setAutoCancel(false)
        NotificationManagerCompat.from(applicationContext).notify(1,notification.build())
        val roomNotification = net.thebookofcode.www.fyp.room.entity.Notification(
            title = "Intruder Alert",
            subject = title!!,
            content = text!!,
            imageUrl = "",
            time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
            date = Calendar.getInstance().time.time
        )
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch { repository.insertNotification(roomNotification) }
        scope.cancel()
    }
}