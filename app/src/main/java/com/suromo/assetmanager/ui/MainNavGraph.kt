package com.suromo.assetmanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suromo.assetmanager.MainDestinations
import com.suromo.assetmanager.data.repository.AppContainer
import com.suromo.assetmanager.ui.home.HomeRoute
import com.suromo.assetmanager.ui.home.HomeViewModel

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   :
 */
@Composable
fun MainNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(appContainer.postsRepository)
            )
            HomeRoute(
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(MainDestinations.ASSET_ROUTE) {
//            val aboutViewModel: AboutViewModel = viewModel(
//                factory = AboutViewModel.provideFactory(appContainer.aboutRepository)
//            )
//            AboutRoute(
//                aboutViewModel = aboutViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.KEEP_ROUTE) {
//            val messageViewModel: MessageViewModel = viewModel(
//                factory = MessageViewModel.provideFactory(appContainer.messageRepository)
//            )
//            MessageRoute(
//                messageViewModel = messageViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.BILL_ROUTE) {
//            val collectionViewModel: CollectionViewModel = viewModel(
//                factory = CollectionViewModel.provideFactory(appContainer.postsRepository)
//            )
//            CollectionRoute(
//                collectionViewModel = collectionViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.USER_ROUTE) {
//            val disclaimerViewModel: DisclaimerViewModel = viewModel(
//                factory = DisclaimerViewModel.provideFactory(appContainer.disclaimerRepository)
//            )
//            DisclaimerRoute(
//                disclaimerViewModel = disclaimerViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.SETTING_ROUTE) {
//            val settingViewModel: SettingViewModel = viewModel(
//                factory = SettingViewModel.provideFactory(appContainer.settingRepository)
//            )
//            SettingRoute(
//                settingViewModel = settingViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
    }
}