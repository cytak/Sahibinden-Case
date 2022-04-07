package com.enes.sahibindenenescase.data.repository

import com.enes.sahibindenenescase.api.ApiService
import com.enes.sahibindenenescase.data.model.UserInformationResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInformationRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUserInformation(id: Long):Response<UserInformationResponse> = apiService.getUserInformation(id)
}