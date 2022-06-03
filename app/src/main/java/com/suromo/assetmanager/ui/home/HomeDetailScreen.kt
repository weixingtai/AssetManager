package com.suromo.assetmanager.ui.home

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.suromo.assetmanager.data.repository.mock.impl.BlockingFakePostsRepository
import com.suromo.assetmanager.ui.home.widget.HomePostList
import com.suromo.assetmanager.ui.rememberContentPaddingForScreen
import com.suromo.assetmanager.ui.theme.AssetManagerTheme
import com.suromo.assetmanager.ui.bean.Result
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/06/2022/6/1
 * desc   :
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeDetailScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onToggleFavorite: (String) -> Unit,
    onSelectPost: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onInteractWithList: () -> Unit,
    onInteractWithDetail: (String) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    articleDetailLazyListStates: Map<String, LazyListState>,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    onSearchInputChanged: (String) -> Unit,
) {
    HomeListScreen(
        uiState = uiState,
        showTopBar = showTopAppBar,
        onRefreshPosts = onRefreshPosts,
        onErrorDismiss = onErrorDismiss,
        openDrawer = openDrawer,
        homeListLazyListState = homeListLazyListState,
        scaffoldState = scaffoldState,
        modifier = modifier,
    ) { hasPostsUiState, contentModifier ->
        val contentPadding = rememberContentPaddingForScreen(additionalTop = 8.dp)
        Row(contentModifier) {
            HomePostList(
                postsFeed = hasPostsUiState.postsFeed,
                onArticleTapped = onSelectPost,
                contentPadding = contentPadding,
                modifier = Modifier
                    .width(334.dp)
                    .notifyInput(onInteractWithList)
                    .imePadding(),
                state = homeListLazyListState
            )
            Crossfade(targetState = hasPostsUiState.selectedPost) { detailPost ->
                val detailLazyListState by derivedStateOf {
                    articleDetailLazyListStates.getValue(detailPost.id)
                }

                key(detailPost.id) {
                    LazyColumn(
                        state = detailLazyListState,
                        contentPadding = contentPadding,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize()
                            .notifyInput {
                                onInteractWithDetail(detailPost.id)
                            }
                            .imePadding()
                    ) {
                        stickyHeader {
                            val context = LocalContext.current
                            TopBar(
                                isFavorite = hasPostsUiState.favorites.contains(detailPost.id),
                                onToggleFavorite = { onToggleFavorite(detailPost.id) },
                                onSharePost = {  },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun Modifier.notifyInput(block: () -> Unit): Modifier =
    composed {
        val blockState = rememberUpdatedState(block)
        pointerInput(Unit) {
            while (currentCoroutineContext().isActive) {
                awaitPointerEventScope {
                    awaitPointerEvent(PointerEventPass.Initial)
                    blockState.value()
                }
            }
        }
    }

@Composable
fun TopBar(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSharePost: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(Dp.Hairline, MaterialTheme.colors.onSurface.copy(alpha = .6f)),
        modifier = modifier.padding(end = 16.dp)
    ) {

    }
}

/**
 * 首页界面预览
 */
@Preview("Home Detail Screen")
@Preview("Home Detail Screen(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Home Detail Screen(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewHomeDetailScreen() {
    val postsFeed = runBlocking {
        (BlockingFakePostsRepository().getPostsFeed() as Result.Success).data
    }
    val uiState = HomeUiState.HasPosts(
        postsFeed = postsFeed,
        selectedPost = postsFeed.allPosts[0],
        favorites = emptySet(),
        isLoading = false,
        errorMessages = emptyList()
    )
    val articleDetailLazyListStates = uiState.postsFeed.allPosts
        .associate { post ->
            key(post.id) {
                post.id to rememberLazyListState()
            }
        }
    AssetManagerTheme {
        HomeDetailScreen(
            uiState = uiState,
            showTopAppBar = true,
            onToggleFavorite = {},
            onSelectPost = {},
            onRefreshPosts = {},
            onErrorDismiss = {},
            onInteractWithList = {},
            onInteractWithDetail = {},
            openDrawer = {},
            homeListLazyListState = rememberLazyListState(),
            articleDetailLazyListStates = articleDetailLazyListStates,
            scaffoldState = rememberScaffoldState(),
            onSearchInputChanged = {}
        )
    }
}