package com.innovation.task.models.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.innovation.task.models.Article

@Database(entities = [Article::class], version = 1)
abstract class PostsDatabase : RoomDatabase(){

    abstract fun postsDao(): PostsDao

}