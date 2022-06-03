package com.suromo.assetmanager.data.repository

import com.suromo.assetmanager.ui.bean.Post
import com.suromo.assetmanager.ui.bean.PostsFeed
import com.suromo.assetmanager.ui.bean.Result
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the Posts data layer.
 */
interface PostsRepository {

    /**
     * Get a specific JetNews post.
     */
    suspend fun getPost(postId: String?): Result<Post>

    /**
     * Get JetNews posts.
     */
    suspend fun getPostsFeed(): Result<PostsFeed>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>>

    /**
     * Toggle a postId to be a favorite or not.
     */
    suspend fun toggleFavorite(postId: String)
}
