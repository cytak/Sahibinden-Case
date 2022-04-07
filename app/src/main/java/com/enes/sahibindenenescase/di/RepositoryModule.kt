package com.enes.sahibindenenescase.di

import com.enes.sahibindenenescase.data.repository.TweetRepository
import com.enes.sahibindenenescase.data.repository.TweetRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideTweetRepository(tweetRepository: TweetRepositoryImpl): TweetRepository
}