package com.innovation.task.models

data class PostsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)