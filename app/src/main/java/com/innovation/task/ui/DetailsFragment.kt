package com.innovation.task.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.innovation.task.R
import com.innovation.task.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailsFragmentArgs by navArgs()
        val post = args.post
        val binding = FragmentDetailsBinding.bind(view)
        binding.tvPostTitle.text = post.title
        Glide.with(this).load(post.urlToImage).into(binding.imgPostDetails)
    }
}