package net.thebookofcode.www.fyp.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import net.thebookofcode.www.fyp.api.ProjectApi
import net.thebookofcode.www.fyp.entity.ServerImages
import net.thebookofcode.www.fyp.entity.ServerMedia
import net.thebookofcode.www.fyp.entity.ServerVideos
import net.thebookofcode.www.fyp.room.dao.NotificationDao
import net.thebookofcode.www.fyp.room.entity.Notification
import javax.inject.Inject

class MainRepository
@Inject constructor(
    private val notificationDao: NotificationDao,
    private val projectApi: ProjectApi
) {
    val getAllNotifications: Flow<List<Notification>> = notificationDao.getAllNotifications()
    suspend fun getOnlinePhotos():ServerImages {
        return projectApi.getOnlinePhotos() }

    suspend fun getOnlineVideos():ServerVideos { return projectApi.getOnlineVideos()}

    suspend fun getOnlineFiles():ServerMedia { return projectApi.getOnlineFiles()}

    suspend fun deleteAllNotifications() {
        notificationDao.deleteAllNotifications()
    }

    suspend fun deleteNotifications(vararg notifications: Notification) {
        for (notification in notifications) {
            notificationDao.deleteNotification(notification)
        }
    }

    suspend fun insertNotification(notification: Notification){
        notificationDao.insertNotification(notification)
    }
}