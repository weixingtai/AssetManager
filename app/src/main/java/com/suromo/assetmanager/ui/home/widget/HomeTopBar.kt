package com.suromo.assetmanager.ui.home.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.suromo.assetmanager.R
import com.suromo.assetmanager.ui.theme.AssetManagerTheme

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/06/2022/6/1
 * desc   : 首页顶部栏
 */
@Composable
fun HomeTopBar(
    elevation: Dp,
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 4.dp, end = 70.dp),
                textAlign = TextAlign.Center,

            )
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    painter = painterResource(R.drawable.ic_drawer_switch),
                    contentDescription = stringResource(R.string.cd_open_navigation_drawer),
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = elevation
    )
}

@Preview("HomeTopBar(Light)")
@Preview("HomeTopBar(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("HomeTopBar(BigFont)", fontScale = 1.5f)
@Composable
fun PreviewHomeTopBar() {
    AssetManagerTheme {
        Surface {
            HomeTopBar(
                elevation = 4.dp,
                openDrawer = {}
            )
        }
    }
}