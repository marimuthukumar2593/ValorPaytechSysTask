package com.example.valorpaytechsystask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.example.valorpaytechsystask.BuildConfig

object Constants {
    const val  BASE_URL ="https://jsonplaceholder.typicode.com"
    const val  BUNDLE_USER ="bundle_user"
    const val  BUNDLE_POST ="bundle_post"
    const val  BUNDLE_USERID ="user_id"
    const val  BUNDLE_POSTID ="post_id"

    fun printDebug(key: String?, msg: Any) {
        if (BuildConfig.DEBUG) {
            if (msg != null) {
                Log.d(key, msg as String)
            }
        }
    }

    fun printError(key: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            if (msg != null) {
                Log.e(key, msg)
            }
        }
    }

    fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}