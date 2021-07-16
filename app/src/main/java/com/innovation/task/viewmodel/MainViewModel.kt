package com.innovation.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.innovation.task.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    /* The Flow which is being collected from either database or server, will be converted to
    LiveData and is going to be observed in the Fragment class to update the UI
     */
    val posts = postsRepository.getPosts().asLiveData()


}