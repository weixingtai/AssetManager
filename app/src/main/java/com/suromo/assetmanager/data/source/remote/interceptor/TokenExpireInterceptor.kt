package com.suromo.assetmanager.data.source.remote.interceptor

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   :
 */
class TokenExpireInterceptor : Interceptor {

    private val gson: Gson by lazy { Gson() }

    @kotlin.jvm.Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response
//        if (response.body() != null && response.body()!!.contentType() != null) {
//            val mediaType = response.body()!!.contentType()
//            val string = response.body()!!.string()
//            val responseBody = ResponseBody.create(mediaType, string)
//            val apiResponse = gson.fromJson(string, ApiResponse::class.java)
//            //判断逻辑 模拟一下
//            if (apiResponse.errorCode == 99999) {
//                //如果是普通的activity话 可以直接跳转，如果是navigation中的fragment，可以发送通知跳转
//                appContext.startActivity(Intent(appContext, TestActivity::class.java).apply {
//                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                })
//            }
//            response.newBuilder().body(responseBody).build()
//        } else {
//            response
//        }
    }
}