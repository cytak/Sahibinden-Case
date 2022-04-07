package com.enes.sahibindenenescase.view

import androidx.annotation.DrawableRes
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.common.BaseUiState
import com.enes.sahibindenenescase.data.model.Data
import com.enes.sahibindenenescase.util.formatDate


data class TweetItemUiState(private val data: Data) : BaseUiState() {

    @DrawableRes fun getImage() = R.drawable.ic_twitter
    fun getSource() = data.source
    fun getText() = data.text
    fun getCreatedDate() = data.created_at?.formatDate()
    fun getId() = data.id

}