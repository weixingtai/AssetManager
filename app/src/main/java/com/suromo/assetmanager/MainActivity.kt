package com.suromo.assetmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.suromo.assetmanager.ui.MainScreen
import com.suromo.assetmanager.util.rememberWindowSizeClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as MainApplication).container
        setContent {
            val windowSizeClass = rememberWindowSizeClass()
            MainScreen(appContainer, windowSizeClass)
        }
    }
}