package com.enes.sahibindenenescase.util

import android.content.Context
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.util.NetworkUtils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable(context)) {
            throw NoConnectionException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectionException : IOException() {
        override val message: String
            get() = super.message ?: context.getString(R.string.no_internet_connection)
    }
}