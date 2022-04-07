package com.enes.sahibindenenescase.data.repository

import com.enes.sahibindenenescase.api.ApiService
import com.enes.sahibindenenescase.data.model.SingleTweetResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetDetailRepository @Inject constructor(private val apiService: ApiService)  {
  suspend fun getTweetDetail(id: Long): Response<SingleTweetResponse> = apiService.getSingleTweet(id)
}