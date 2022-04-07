package com.enes.sahibindenenescase.di

import android.content.Context
import com.enes.sahibindenenescase.BuildConfig
import com.enes.sahibindenenescase.api.ApiService
import com.enes.sahibindenenescase.util.Constants
import com.enes.sahibindenenescase.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECT_TIMEOUT = 50L
    private const val READ_TIMEOUT = 100L
    private const val WRITE_TIMEOUT = 100L


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + Constants.BEARER_TOKEN)
                    .build()
                it.proceed(request)
            })
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .connectTimeout(CONNECT_TIMEOUT, SECONDS)
            .readTimeout(READ_TIMEOUT, SECONDS)
            .writeTimeout(WRITE_TIMEOUT, SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}