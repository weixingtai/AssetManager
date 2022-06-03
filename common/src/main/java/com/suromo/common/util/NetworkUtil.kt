package com.suromo.common.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   :
 */
object NetworkUtil {
    fun isNetworkAvailable(application: Application?): Boolean {
        val manager = application?.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager ?: return false
        val info = manager.activeNetworkInfo
        return null != info && info.isAvailable
    }
}