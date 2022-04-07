package com.enes.sahibindenenescase.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.databinding.FragmentTweetDetailBinding
import com.enes.sahibindenenescase.viewmodel.TweetDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TweetDetailFragment: Fragment() {
    private lateinit var binding: FragmentTweetDetailBinding
    val viewModel: TweetDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tweet_detail, container, false)
        val id = arguments?.getString("tweetId","")
        id?.let { viewModel.getTweetDetail(it) }
        viewModel.tweetDetailUiState.observe(viewLifecycleOwner){
            binding.tweetDetailUiState = it
        }
        setListener()
        return binding.root
    }

    private fun setListener(){
        binding.imgBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
