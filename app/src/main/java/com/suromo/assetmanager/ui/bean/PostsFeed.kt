package com.suromo.assetmanager.ui.bean

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/06/2022/6/1
 * desc   :
 */
data class PostsFeed(
    val todayPosts: List<Post>
) {
    val allPosts: List<Post> = todayPosts
}