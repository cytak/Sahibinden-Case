package com.enes.sahibindenenescase.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enes.sahibindenenescase.util.Constants
import com.enes.sahibindenenescase.util.logData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.User
import twitter4j.auth.AccessToken
import twitter4j.conf.ConfigurationBuilder
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val sharedPreferences: SharedPreferences): ViewModel() {
    lateinit var twitter: Twitter
    private val _liveDataUrl = MutableLiveData<String>()
    val liveDataUrl: LiveData<String> = _liveDataUrl

    fun getLoginRequest(){
        viewModelScope.launch(Dispatchers.Default) {
            val builder = ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(Constants.API_KEY)
                .setOAuthConsumerSecret(Constants.API_KEY_SECRET)
                .setIncludeEmailEnabled(true)
            val config = builder.build()
            val factory = TwitterFactory(config)
            twitter = factory.instance
            try {
                _liveDataUrl.postValue(twitter.oAuthRequestToken?.authenticationURL)

            } catch (e: IllegalStateException) {
                logData("ERROR: ${e.message.toString()}")
            }
        }

    }

    fun saveUserData(accToken: AccessToken, user: User?) {
        Constants.USER_ID = user?.id!!
        Constants.ACCESS_TOKEN = accToken.token
        Constants.ACCESS_TOKEN_SECRET = accToken.tokenSecret
        sharedPreferences.edit().putString(Constants.OAUTH_TOKEN, accToken.token).apply()
        sharedPreferences.edit().putString(Constants.OAUTH_TOKEN_SECRET, accToken.token).apply()
        sharedPreferences.edit().putLong(Constants.TWITTER_USER_ID, user.id).apply()

    }




}