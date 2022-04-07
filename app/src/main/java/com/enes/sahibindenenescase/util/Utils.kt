package com.enes.sahibindenenescase.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

object NetworkUtils {
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }
}

fun String.formatDate():String{
    var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT)
    val newDate: Date = format.parse(this) as Date
    format = SimpleDateFormat("dd.MM.yyyy hh:mm",Locale.ROOT)
    return format.format(newDate)
}

fun Fragment.logData(message:String){
    Log.d(this.javaClass.simpleName, "Log message: $message")
}

fun ViewModel.logData(message: String){
    Log.d(this.javaClass.simpleName,"Log message: $message")
}