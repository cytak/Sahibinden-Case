package com.enes.sahibindenenescase.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.enes.sahibindenenescase.data.model.Data
import com.enes.sahibindenenescase.data.pagingdatasource.TweetPagingDataSource
import com.enes.sahibindenenescase.api.ApiService
import com.enes.sahibindenenescase.util.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TweetRepositoryImpl @Inject constructor(private val apiService: ApiService) : TweetRepository {
    override fun getTweet(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE), pagingSourceFactory = { TweetPagingDataSource(apiService) }).flow
    }
}