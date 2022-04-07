package com.enes.sahibindenenescase.view

import android.content.Context
import androidx.paging.LoadState
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.common.BaseUiState


data class TweetsUiState(
    private val loadState: LoadState
) : BaseUiState() {

    fun getProgressBarVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)
    fun getListVisibility() = getViewVisibility(isVisible = loadState is LoadState.NotLoading)
    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)
    fun getErrorMessage(context: Context) = if (loadState is LoadState.Error) {
        loadState.error.localizedMessage ?: context.getString(R.string.something_went_wrong)
    } else ""
}