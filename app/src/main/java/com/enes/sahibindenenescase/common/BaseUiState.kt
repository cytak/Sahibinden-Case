package com.enes.sahibindenenescase.common

import android.view.View


open class BaseUiState {
    fun getViewVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.GONE
}