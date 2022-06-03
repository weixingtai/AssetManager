package com.suromo.assetmanager.data.repository

import android.content.Context
import com.suromo.assetmanager.data.repository.mock.impl.FakePostsRepository

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   :
 */
interface AppContainer {
    val postsRepository: PostsRepository
}
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository()
    }

}