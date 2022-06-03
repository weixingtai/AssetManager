package com.suromo.assetmanager.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
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
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToAsset: () -> Unit,
    navigateToBill: () -> Unit,
    navigateToUser: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        MagicLogo(Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = Icons.Filled.Home,
            label = stringResource(id = R.string.detail),
            isSelected = currentRoute == MainDestinations.HOME_ROUTE,
            action = {
                navigateToHome()
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Filled.PersonPin,
            label = stringResource(id = R.string.asset),
            isSelected = currentRoute == MainDestinations.ASSET_ROUTE,
            action = {
                navigateToAsset()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Filled.Message,
            label = stringResource(id = R.string.bill),
            isSelected = currentRoute == MainDestinations.BILL_ROUTE,
            action = {
                navigateToBill()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Filled.Collections,
            label = stringResource(id = R.string.user),
            isSelected = currentRoute == MainDestinations.USER_ROUTE,
            action = {
                navigateToUser()
                closeDrawer()
            }
        )
    }
}

@Composable
private fun MagicLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.ic_magic_wordmark),
            contentDescription = stringResource(R.string.app_name),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
        )
    }
}

@Composable
private fun DrawerButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationIcon(
                    icon = icon,
                    isSelected = isSelected,
                    contentDescription = null, // decorative
                    tintColor = textIconColor
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    AssetManagerTheme {
        Surface {
            AppDrawer(
                currentRoute = MainDestinations.HOME_ROUTE,
                navigateToHome = {},
                navigateToAsset = {},
                navigateToBill = {},
                navigateToUser = {},
                closeDrawer = { }
            )
        }
    }
}