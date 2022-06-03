package com.suromo.assetmanager.ui

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.assetmanager.MainDestinations
import com.suromo.assetmanager.R
import com.suromo.assetmanager.ui.theme.AssetManagerTheme
import com.suromo.assetmanager.widget.NavigationIcon

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/06/2022/6/1
 * desc   :
 */
@Composable
fun MagicNavRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    NavigationRail(
        modifier = modifier,
        elevation = 0.dp,
        header = header
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Composable
fun MainNavRail(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToAsset: () -> Unit,
    navigateToBill: () -> Unit,
    navigateToUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    MagicNavRail(
        modifier = modifier
    ) {
        NavRailIcon(
            icon = Icons.Filled.Home,
            contentDescription = stringResource(id = R.string.detail),
            isSelected = currentRoute == MainDestinations.HOME_ROUTE,
            action = navigateToHome
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavRailIcon(
            icon = Icons.Filled.PersonPin,
            contentDescription = stringResource(id = R.string.asset),
            isSelected = currentRoute == MainDestinations.ASSET_ROUTE,
            action = navigateToAsset
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavRailIcon(
            icon = Icons.Filled.Message,
            contentDescription = stringResource(id = R.string.bill),
            isSelected = currentRoute == MainDestinations.BILL_ROUTE,
            action = navigateToBill
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavRailIcon(
            icon = Icons.Filled.Collections,
            contentDescription = stringResource(id = R.string.user),
            isSelected = currentRoute == MainDestinations.USER_ROUTE,
            action = navigateToUser
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NavRailIcon(
    icon: ImageVector,
    contentDescription: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor by animateColorAsState(
        if (isSelected) {
            MaterialTheme.colors.primary.copy(alpha = 0.12f)
        } else {
            Color.Transparent
        }
    )

    Surface(
        selected = isSelected,
        color = backgroundColor,
        onClick = action,
        shape = CircleShape,
        modifier = modifier.size(48.dp)
    ) {
        NavigationIcon(
            icon = icon,
            isSelected = isSelected,
            contentDescription = contentDescription,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppNavRail() {
    AssetManagerTheme {
        MainNavRail(
            currentRoute = MainDestinations.HOME_ROUTE,
            navigateToHome = {},
            navigateToAsset = {},
            navigateToBill = {},
            navigateToUser = {}
        )
    }
}