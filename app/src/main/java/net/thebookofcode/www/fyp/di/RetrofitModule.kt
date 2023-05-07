package net.thebookofcode.www.fyp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.thebookofcode.www.fyp.api.ProjectApi
import net.thebookofcode.www.fyp.util.Constants.Companion.BASE_URL
import net.thebookofcode.www.fyp.util.Constants.Companion.WEB_PORT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("$BASE_URL:$WEB_PORT/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideProjectApi(retrofit: Retrofit.Builder):ProjectApi{
        return retrofit.build()
            .create(ProjectApi::class.java)
    }

}