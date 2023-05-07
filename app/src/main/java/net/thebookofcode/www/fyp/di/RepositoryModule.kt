package net.thebookofcode.www.fyp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.thebookofcode.www.fyp.api.ProjectApi
import net.thebookofcode.www.fyp.repository.MainRepository
import net.thebookofcode.www.fyp.room.dao.NotificationDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        notificationDao: NotificationDao,
        projectApi: ProjectApi
    ): MainRepository {
        return MainRepository(notificationDao,projectApi)
    }
}