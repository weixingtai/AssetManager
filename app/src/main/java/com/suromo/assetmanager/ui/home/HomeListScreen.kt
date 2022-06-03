package com.suromo.assetmanager.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.orhanobut.logger.Logger
import com.suromo.assetmanager.R
import com.suromo.assetmanager.data.repository.mock.impl.BlockingFakePostsRepository
import com.suromo.assetmanager.ui.bean.Result
import com.suromo.assetmanager.ui.home.widget.HomeTopBar
import com.suromo.assetmanager.ui.theme.AssetManagerTheme
import com.suromo.assetmanager.util.isScrolled
import kotlinx.coroutines.runBlocking

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/06/2022/6/1
 * desc   :
 */
@Composable
fun HomeListScreen(
    uiState: HomeUiState,
    showTopBar: Boolean,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    hasPostsContent: @Composable (
        uiState: HomeUiState.HasPosts,
        modifier: Modifier
    ) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = it) },
        topBar = {
            if (showTopBar) {
                HomeTopBar(
                    openDrawer = openDrawer,
                    elevation = if (!homeListLazyListState.isScrolled) 0.dp else 4.dp
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)
        Column {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        modifier =
                        Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .clickable {

                                       },
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "2021年01月",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 1.dp, end = 5.dp)
                        )
                        Text(text = "25日", fontSize = 20.sp)
                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")

                    }
                    Icon(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .clickable {
                                Logger.d("reset")
                            },
                        imageVector = Icons.Filled.RestartAlt,
                        contentDescription = ""
                    )
                }

                Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "收入")
                        Text(text = "6400.00")
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "支出")
                        Text(text = "298.00")
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "结余")
                        Text(text = "298.00")
                    }
                }
            }

            LoadingContent(
                empty = when (uiState) {
                    is HomeUiState.HasPosts -> false
                    is HomeUiState.NoPosts -> uiState.isLoading
                },
                emptyContent = { FullScreenLoading() },
                loading = uiState.isLoading,
                onRefresh = onRefreshPosts,
                content = {
                    when (uiState) {
                        is HomeUiState.HasPosts -> hasPostsContent(uiState, contentModifier)
                        is HomeUiState.NoPosts -> {
                            if (uiState.errorMessages.isEmpty()) {
                                TextButton(
                                    onClick = onRefreshPosts,
                                    modifier.fillMaxSize()
                                ) {
                                    Text(
                                        stringResource(id = R.string.home_tap_to_load_content),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            } else {
                                Box(contentModifier.fillMaxSize()) { /* empty screen */ }
                            }
                        }
                    }
                }
            )
        }
    }

    if (uiState.errorMessages.isNotEmpty()) {
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }
        val errorMessageText: String = stringResource(errorMessage.messageId)
        val retryMessageText = stringResource(id = R.string.retry)
        val onRefreshPostsState by rememberUpdatedState(onRefreshPosts)
        val onErrorDismissState by rememberUpdatedState(onErrorDismiss)

        LaunchedEffect(errorMessageText, retryMessageText, scaffoldState) {
            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessageText,
                actionLabel = retryMessageText
            )
            if (snackBarResult == SnackbarResult.ActionPerformed) {
                onRefreshPostsState()
            }
            onErrorDismissState(errorMessage.id)
        }
    }
}

/**
 * 全屏加载
 */
@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

/**
 * 首页内容加载
 */
@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(loading),
            onRefresh = onRefresh,
            content = content,
        )
    }
}
@Preview("HomeListScreen(Light)")
@Preview("HomeListScreen(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("HomeListScreen(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewHomeListScreen() {
    val postsFeed = runBlocking {
        (BlockingFakePostsRepository().getPostsFeed() as Result.Success).data
    }
    AssetManagerTheme {
        HomeListScreen(
            uiState  = HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedPost = postsFeed.allPosts[0],
                favorites = emptySet(),
                isLoading = false,
                errorMessages = emptyList()
            ),
            showTopBar = true,
            onRefreshPosts = {},
            onErrorDismiss = {},
            openDrawer = {},
            homeListLazyListState = rememberLazyListState(),
            scaffoldState = rememberScaffoldState()
        ){ hasPostsUiState, contentModifier ->

        }
    }
}