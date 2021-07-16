package com.innovation.task.di

import android.app.Application
import androidx.room.Room
import com.innovation.task.api.PostsAPI
import com.innovation.task.models.data.PostsDatabase
import com.innovation.task.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providePostsApi(): PostsAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PostsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PostsDatabase =
        Room.databaseBuilder(app, PostsDatabase::class.java, "posts_database")
            .build()

}