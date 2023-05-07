package net.thebookofcode.www.fyp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.thebookofcode.www.fyp.room.dao.NotificationDao
import net.thebookofcode.www.fyp.room.database.NotificationDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideNotificationDB(@ApplicationContext context: Context) : NotificationDatabase{
        return Room.databaseBuilder(
            context,
            NotificationDatabase::class.java,
            NotificationDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNotificationDao(notificationDatabase: NotificationDatabase) : NotificationDao{
        return notificationDatabase.notificationDao()
    }
}