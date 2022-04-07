package com.enes.sahibindenenescase.view

import androidx.annotation.DrawableRes
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.common.BaseUiState
import com.enes.sahibindenenescase.data.model.Data
import com.enes.sahibindenenescase.util.formatDate

class TweetDetailUiState(private val data: Data): BaseUiState() {

    @DrawableRes
    fun getImage() = R.drawable.ic_twitter
    fun getDate() = data.created_at?.formatDate()
    fun getText() = data.text
    fun getSource() = data.source
    fun getAuthorId() = data.authorId
    fun isSourceVisibility() = getViewVisibility(data.source.isNotEmpty())
    fun isDateVisibility() = getViewVisibility(!data.created_at.isNullOrEmpty())
    fun isAuthorIdVisibility() = getViewVisibility(!data.authorId.isNullOrEmpty())

}