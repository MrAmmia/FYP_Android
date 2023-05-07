package net.thebookofcode.www.fyp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.thebookofcode.www.fyp.room.entity.Notification


@Dao
interface NotificationDao {
    @Query("SELECT * FROM Notification ORDER BY date DESC")
    fun getAllNotifications() : Flow<List<Notification>>

    @Delete
    suspend fun deleteNotification(notification: Notification)

    @Query("DELETE FROM Notification")
    suspend fun deleteAllNotifications()

    @Insert
    suspend fun insertNotification(notification: Notification)
}