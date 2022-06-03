package com.suromo.assetmanager.ui.home.widget

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.orhanobut.logger.Logger
import com.suromo.assetmanager.data.repository.mock.post
import com.suromo.assetmanager.ui.bean.Post
import com.suromo.assetmanager.ui.theme.AssetManagerTheme
import com.suromo.assetmanager.ui.theme.Gray900
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   : 首页卡片条目展示
 */
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun PostCard(post: Post,modifier: Modifier = Modifier) {
    val swipeSize = 60.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val swipeState = rememberSwipeableState(0)

    val swipePxSize = with(LocalDensity.current) { swipeSize.toPx() }
    val anchors = mapOf(0f to 0, -swipePxSize to 1)

    val coroutineScope = rememberCoroutineScope()

    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .width(screenWidth + swipeSize)
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .onFocusedBoundsChanged {
                Logger.d("onFocusChanged")
                coroutineScope.launch {
                    swipeState.animateTo(0)
                }
            }
            .background(Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .width(swipeSize + 20.dp)
                .height(40.dp)
                .background(Color.Red)
                .clickable {
                    Logger.d("删除")
                    coroutineScope.launch {
                        swipeState.animateTo(0)
                    }
                }
                .padding(end = 20.dp)
            ) {
            Text(text = "删除",color = Color.White)
        }
        Row(
            Modifier
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
                .width(screenWidth)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                .width(screenWidth)
                .background(Color.White)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val imageModifier = Modifier
                        .heightIn(min = 20.dp)
                        .wrapContentHeight()
                        .clip(shape = MaterialTheme.shapes.medium)
                    Box(
                        Modifier
                            .background(color = Gray900, shape = RoundedCornerShape(50.dp))
                            .padding(8.dp)){
                        Icon(
                            imageVector = post.imageId,
                            contentDescription = null,
                            modifier = imageModifier,
                            tint = MaterialTheme.colors.primary

                        )
                    }
                    Text(
                        text = post.title,
                        style = typography.body1,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )

                    Text(
                        text = post.description,
                        style = typography.caption,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )
                }
                Text(
                    text = post.price,
                    style = typography.body1,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                )
            }

        }
    }
}

@Preview("Home PostCard")
@Preview("Home PostCard(Dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Home PostCard(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewHomePostCard() {
    val post = post

    AssetManagerTheme {
        Surface {
            PostCard(post)
        }
    }
}
