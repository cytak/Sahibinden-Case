package com.enes.sahibindenenescase.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enes.sahibindenenescase.data.repository.UserInformationRepository
import com.enes.sahibindenenescase.util.Constants
import com.enes.sahibindenenescase.view.UserInformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userInformationRepository: UserInformationRepository,private val sharedPreferences: SharedPreferences): ViewModel() {
    private val _userInformationUiState = MutableLiveData<UserInformationUiState>()
    val userInformationUiState: LiveData<UserInformationUiState> = _userInformationUiState
    private val _logOut = MutableLiveData<Boolean>()
    val logOut: LiveData<Boolean> = _logOut


    fun getUserInformation() {
        val userId = sharedPreferences.getLong(Constants.TWITTER_USER_ID,0L)
        viewModelScope.launch {
            val response =  userInformationRepository.getUserInformation(userId)
            if (response.isSuccessful){
                response.body()?.let { singleTweetResponse ->
                    _userInformationUiState.postValue( UserInformationUiState(singleTweetResponse.data))
                }
            }
        }
    }

    fun getSignOut(){
        Constants.ACCESS_TOKEN = ""
        Constants.ACCESS_TOKEN_SECRET = ""
        Constants.USER_ID = 0L
        sharedPreferences.edit().clear().apply()
        _logOut.postValue(true)

    }
}