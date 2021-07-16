package com.innovation.task.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovation.task.R
import com.innovation.task.utils.Resource
import com.innovation.task.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts.*

@AndroidEntryPoint
class PostsFragment : Fragment(R.layout.fragment_posts) {

    private lateinit var postsAdapter: PostsAdapter
    private val viewModel: MainViewModel by viewModels()
    private var isLoading = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        postsAdapter.setOnItemClickListener {
            val action = PostsFragmentDirections.actionPostsFragmentToDetailsFragment(it)
            findNavController().navigate(
                action
            )
        }


        showProgressBar()
        viewModel.posts.observe(viewLifecycleOwner, { response ->
            when(response){
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Success -> {
                    hideProgressBar()
                    postsAdapter.differ.submitList(response.data?.toList())
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(),"An error occurred. Couldn't load data from server!",Toast.LENGTH_SHORT).show()
                    postsAdapter.differ.submitList(response.data?.toList())
                }
            }

        })


    }

    private fun hideProgressBar() {
        pagination_progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        pagination_progressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun setupRecyclerView() {
        postsAdapter = PostsAdapter()
        recyclerview_posts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}