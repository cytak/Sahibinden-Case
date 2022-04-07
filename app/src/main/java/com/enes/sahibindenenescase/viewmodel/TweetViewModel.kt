package com.enes.sahibindenenescase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.enes.sahibindenenescase.data.repository.TweetRepository
import com.enes.sahibindenenescase.view.TweetItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(tweetRepository: TweetRepository) : ViewModel() {
    val tweetItemsUiStates = tweetRepository.getTweet()
        .map { pagingData ->
            pagingData.map { data -> TweetItemUiState(data) }
        }.cachedIn(viewModelScope)
}
