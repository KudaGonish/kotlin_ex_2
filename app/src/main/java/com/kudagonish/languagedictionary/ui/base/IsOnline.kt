package com.kudagonish.languagedictionary.ui.base

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService


    fun isOnline(context: Context): Boolean{
        val connectivityManager = context.getSystemService<ConnectivityManager>()
        val netInf =connectivityManager?.activeNetworkInfo
        return netInf != null && netInf.isConnected
    }
