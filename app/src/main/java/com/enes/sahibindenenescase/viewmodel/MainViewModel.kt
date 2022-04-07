package com.enes.sahibindenenescase.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enes.sahibindenenescase.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) : ViewModel(){

    private val _loginControl = MutableLiveData<Boolean>()
    val loginControl: LiveData<Boolean> = _loginControl

    fun getUserData() {
        Constants.ACCESS_TOKEN = sharedPreferences.getString(Constants.OAUTH_TOKEN,"").toString()
        Constants.ACCESS_TOKEN_SECRET = sharedPreferences.getString(Constants.OAUTH_TOKEN_SECRET,"").toString()
        Constants.USER_ID = sharedPreferences.getLong(Constants.TWITTER_USER_ID,0L)
        _loginControl.postValue(Constants.ACCESS_TOKEN.isNotEmpty())
    }
}