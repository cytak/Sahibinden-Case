package com.enes.sahibindenenescase.data.repository

import androidx.paging.PagingData
import com.enes.sahibindenenescase.data.model.Data
import kotlinx.coroutines.flow.Flow


interface TweetRepository {
    fun getTweet(): Flow<PagingData<Data>>
}