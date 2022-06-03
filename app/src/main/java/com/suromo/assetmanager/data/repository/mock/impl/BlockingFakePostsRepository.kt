package com.suromo.assetmanager.data.repository.mock.impl

import com.suromo.assetmanager.data.repository.PostsRepository
import com.suromo.assetmanager.data.repository.mock.posts
import com.suromo.assetmanager.ui.bean.Post
import com.suromo.assetmanager.ui.bean.PostsFeed
import com.suromo.assetmanager.util.addOrRemove
import com.suromo.assetmanager.ui.bean.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   :
 */
class BlockingFakePostsRepository : PostsRepository {

    // for now, keep the favorites in memory
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    override suspend fun getPost(postId: String?): Result<Post> {
        return withContext(Dispatchers.IO) {
            val post = posts.allPosts.find { it.id == postId }
            if (post == null) {
                Result.Error(IllegalArgumentException("Unable to find post"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        return Result.Success(posts)
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites

    override suspend fun toggleFavorite(postId: String) {
        val set = favorites.value.toMutableSet()
        set.addOrRemove(postId)
        favorites.value = set
    }
}