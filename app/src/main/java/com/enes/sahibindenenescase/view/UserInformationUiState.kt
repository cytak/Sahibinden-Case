package com.enes.sahibindenenescase.view

import com.enes.sahibindenenescase.common.BaseUiState
import com.enes.sahibindenenescase.data.model.Data
import com.enes.sahibindenenescase.util.formatDate

class UserInformationUiState(private val data:Data): BaseUiState() {

    fun getUserImage() = data.profile_image_url
    fun getDescription() = data.description
    fun getName() = data.name
    fun getDate() = data.created_at?.formatDate()
    fun getLocation() = data.location
    fun getUserName() = data.username
    fun getFollowersCount() = data.public_metrics?.followers_count
    fun getFollowingCount() = data.public_metrics?.following_count
    fun getTweetCount() = data.public_metrics?.tweet_count
    fun getListedCount() = data.public_metrics?.listed_count
}