package com.suromo.assetmanager

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   :
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val ASSET_ROUTE = "asset"
    const val KEEP_ROUTE = "keep"
    const val BILL_ROUTE = "bill"
    const val USER_ROUTE = "user"
    const val SETTING_ROUTE = "setting"

}

/**
 * Models the navigation actions in the app.
 */
class MainNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(MainDestinations.HOME_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToAsset: () -> Unit = {
        navController.navigate(MainDestinations.ASSET_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToKeep: () -> Unit = {
        navController.navigate(MainDestinations.KEEP_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToBill: () -> Unit = {
        navController.navigate(MainDestinations.BILL_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToUser: () -> Unit = {
        navController.navigate(MainDestinations.USER_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSetting: () -> Unit = {
        navController.navigate(MainDestinations.SETTING_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}