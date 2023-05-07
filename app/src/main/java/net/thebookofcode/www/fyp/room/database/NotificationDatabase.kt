package net.thebookofcode.www.fyp.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.thebookofcode.www.fyp.room.dao.NotificationDao
import net.thebookofcode.www.fyp.room.entity.Notification

@Database(entities = [Notification::class], version = 1)
abstract class NotificationDatabase:RoomDatabase() {
    abstract fun notificationDao() : NotificationDao

    companion object{
        val DATABASE_NAME:String = "notifications_db"
    }
}