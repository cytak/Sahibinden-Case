package com.enes.sahibindenenescase.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.fragment.app.viewModels
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.common.FooterAdapter
import com.enes.sahibindenenescase.databinding.FragmentTweetBinding
import com.enes.sahibindenenescase.util.Router
import com.enes.sahibindenenescase.util.collect
import com.enes.sahibindenenescase.util.collectLast
import com.enes.sahibindenenescase.util.executeWithAction
import com.enes.sahibindenenescase.view.TweetItemUiState
import com.enes.sahibindenenescase.viewmodel.TweetViewModel
import com.enes.sahibindenenescase.view.TweetsUiState
import com.enes.sahibindenenescase.view.adapter.TweetAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import okhttp3.Route
import javax.inject.Inject

@AndroidEntryPoint
class TweetFragment : Fragment() {
    private lateinit var binding: FragmentTweetBinding
    val viewModel: TweetViewModel by viewModels()

    @Inject
    lateinit var tweetAdapter: TweetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tweet,container,false)
        setListener()
        setAdapter()
        collectLast(viewModel.tweetItemsUiStates, ::setTweets)
        return binding.root
    }

    private fun setListener() {
        binding.btnRetry.setOnClickListener { tweetAdapter.retry() }
        binding.imgUsPr.setOnClickListener{
            Router.createFragment(requireActivity(),UserFragment())
        }
        tweetAdapter.tweetClicked {
            val bundle = Bundle()
            bundle.putString("tweetId",it)
            val tweetDetailFragment = TweetDetailFragment()
            tweetDetailFragment.arguments = bundle
            Router.createFragment(requireActivity(),tweetDetailFragment)
        }
    }


    private fun setAdapter() {
        collect(flow = tweetAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setTweetsUiState
        )
        binding.rcylerTweets.adapter = tweetAdapter.withLoadStateFooter(FooterAdapter(tweetAdapter::retry))
    }

    private fun setTweetsUiState(loadState: LoadState) {
        binding.executeWithAction {
            tweetUiState = TweetsUiState(loadState)
        }
    }

    private suspend fun setTweets(tweetItemsPagingData: PagingData<TweetItemUiState>) {
        tweetAdapter.submitData(tweetItemsPagingData)
    }

}