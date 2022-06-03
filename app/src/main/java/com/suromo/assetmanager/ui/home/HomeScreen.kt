package com.suromo.assetmanager.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.assetmanager.data.repository.mock.impl.BlockingFakePostsRepository
import com.suromo.assetmanager.ui.home.widget.HomePostList
import com.suromo.assetmanager.ui.rememberContentPaddingForScreen
import com.suromo.assetmanager.ui.theme.AssetManagerTheme
import com.suromo.assetmanager.ui.bean.Result
import kotlinx.coroutines.runBlocking

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/06/2022/6/1
 * desc   :
 */
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    showTopBar: Boolean,
    onSelectPost: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier
) {

    HomeListScreen(
        uiState = uiState,
        showTopBar = showTopBar,
        onRefreshPosts = onRefreshPosts,
        onErrorDismiss = onErrorDismiss,
        openDrawer = openDrawer,
        homeListLazyListState = homeListLazyListState,
        scaffoldState = scaffoldState,
        modifier = modifier
    ) { hasPostsUiState, contentModifier ->
        HomePostList(
            postsFeed = hasPostsUiState.postsFeed,
            onArticleTapped = onSelectPost,
            contentPadding = rememberContentPaddingForScreen(
                additionalTop = if (showTopBar) 0.dp else 8.dp
            ),
            modifier = contentModifier,
            state = homeListLazyListState
        )
    }
}

/**
 * 首页界面预览
 */
@Preview("Home Screen")
@Preview("Home Screen(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Home Screen(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewHomeScreen() {
    val postsFeed = runBlocking {
        (BlockingFakePostsRepository().getPostsFeed() as Result.Success).data
    }
    AssetManagerTheme {
        HomeScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedPost = postsFeed.allPosts[0],
                favorites = emptySet(),
                isLoading = false,
                errorMessages = emptyList()
            ),
            showTopBar = true,
            onSelectPost = {},
            onRefreshPosts = {},
            onErrorDismiss = {},
            openDrawer = {},
            homeListLazyListState = rememberLazyListState(),
            scaffoldState = rememberScaffoldState()
        )
    }
}