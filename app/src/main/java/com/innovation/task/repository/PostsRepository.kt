package com.innovation.task.repository

import androidx.room.withTransaction
import com.innovation.task.api.PostsAPI
import com.innovation.task.models.data.PostsDatabase
import com.innovation.task.utils.networkBoundResource
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsAPI: PostsAPI,
    private val db: PostsDatabase
) {
    private val postsDao = db.postsDao()

    /* Here I implemented the caching logic and whether the app should fetch data using network
    or Just show cached data from the database. And if it's going to get data from remote server or
    from the local database, this data will be collected using Kotlin Flow
     */

    fun getPosts() = networkBoundResource(
        query = {
            postsDao.getAllPosts()
        },
        fetch = {

            postsAPI.getPosts()
        },
        saveFetchResult = { posts ->
            db.withTransaction {
                postsDao.deleteAllPosts()
                posts.body()?.articles?.let { postsDao.insertPosts(it) }
            }
        }
    )


}