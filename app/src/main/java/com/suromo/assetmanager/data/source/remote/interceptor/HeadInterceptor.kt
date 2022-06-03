package com.suromo.assetmanager.data.source.remote.interceptor

import com.suromo.assetmanager.util.CacheUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   :
 */
class HeadInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token123456").build()
        builder.addHeader("device", "Android").build()
        builder.addHeader("isLogin", CacheUtil.isLogin().toString()).build()
        return chain.proceed(builder.build())
    }
}