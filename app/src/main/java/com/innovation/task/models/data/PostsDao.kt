package com.innovation.task.models.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.innovation.task.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {

    /* this function creates asynchronous stream of values. Flow can keep emitting list
       of posts and we will observe them one after the other
     */
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(articles: List<Article>)

    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()

}