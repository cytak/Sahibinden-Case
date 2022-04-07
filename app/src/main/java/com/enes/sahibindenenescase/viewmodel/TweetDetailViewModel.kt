package com.enes.sahibindenenescase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enes.sahibindenenescase.data.repository.TweetDetailRepository
import com.enes.sahibindenenescase.view.TweetDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetDetailViewModel @Inject constructor(private val tweetDetailRepository: TweetDetailRepository) :ViewModel(){

    private val _tweetDetailUiState = MutableLiveData<TweetDetailUiState>()
    val tweetDetailUiState: LiveData<TweetDetailUiState> = _tweetDetailUiState

    fun getTweetDetail(id: String) {
        viewModelScope.launch {
            val response =  tweetDetailRepository.getTweetDetail(id.toLong())
            if (response.isSuccessful){
                response.body()?.let { singleTweetResponse ->
                    _tweetDetailUiState.postValue( TweetDetailUiState(singleTweetResponse.data))
                }
            }
        }
    }

}