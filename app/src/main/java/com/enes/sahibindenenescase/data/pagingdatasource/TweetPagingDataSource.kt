package com.enes.sahibindenenescase.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.enes.sahibindenenescase.data.model.Data
import com.enes.sahibindenenescase.api.ApiService
import com.enes.sahibindenenescase.util.Constants


class TweetPagingDataSource(private val apiService: ApiService) :
    PagingSource<String, Data>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Data> {
        val page = params.key ?: STARTING_PAGE_TOKEN
        return try {
            val response = apiService.geTweet(Constants.USER_ID,page)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == STARTING_PAGE_TOKEN) null else response.meta.previous_token,
                nextKey = if (response.data.isEmpty()) null else response.meta.next_token
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<String, Data>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey // prevKey
                ?: state.closestPageToPosition(anchorPosition)?.prevKey //nextKey
        }
    }

    companion object {
        private const val STARTING_PAGE_TOKEN = "7140dibdnow9c7btw3w4d8uspwtsckabqx1rmqpmu09iu"
    }

}
