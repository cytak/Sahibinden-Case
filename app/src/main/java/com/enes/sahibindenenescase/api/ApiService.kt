package com.enes.sahibindenenescase.api

import com.enes.sahibindenenescase.data.model.SingleTweetResponse
import com.enes.sahibindenenescase.data.model.TweetResponse
import com.enes.sahibindenenescase.data.model.UserInformationResponse
import com.enes.sahibindenenescase.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET(Constants.TWEET_LIST)
    suspend fun geTweet(@Path("id")id: Long, @Query("pagination_token") paginationToken: String? = "" ,@Query("tweet.fields") tweetFields: String? = "source,text,created_at"): TweetResponse

    @GET(Constants.SINGLE_TWEET)
    suspend fun getSingleTweet(@Path("id")id:Long,@Query("media.fields") mediaFields: String?= "preview_image_url,url", @Query("tweet.fields") tweetFields: String? = "attachments,author_id,source,text,created_at"):Response<SingleTweetResponse>

    @GET(Constants.USER_INFORMATION)
    suspend fun getUserInformation(@Path("id")id: Long,@Query("user.fields") userFields: String?= "created_at,description,entities,id,location,name,pinned_tweet_id,profile_image_url,protected,public_metrics,url,username,verified,withheld"): Response<UserInformationResponse>


}