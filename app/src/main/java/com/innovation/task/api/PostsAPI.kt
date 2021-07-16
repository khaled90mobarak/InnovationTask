package com.innovation.task.api

import com.innovation.task.models.PostsResponse
import com.innovation.task.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsAPI {
    @GET("v2/top-headlines")
    suspend fun getPosts(
        @Query("country")
        countryCode: String = "us",
        @Query("apikey")
        apikey: String = API_KEY
    ): Response<PostsResponse>
}