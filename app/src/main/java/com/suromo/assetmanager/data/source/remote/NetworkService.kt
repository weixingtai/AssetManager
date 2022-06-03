package com.suromo.assetmanager.data.source.remote

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   :
 */
interface NetworkService {

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }

    /**
     * 登录
     */
//    @FormUrlEncoded
//    @POST("user/login")
//    suspend fun login(
//        @Field("username") username: String,
//        @Field("password") pwd: String
//    ): ApiResponse<UserInfo>
}