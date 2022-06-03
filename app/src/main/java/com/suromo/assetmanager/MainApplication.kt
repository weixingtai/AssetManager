package com.suromo.assetmanager

import com.suromo.assetmanager.data.repository.AppContainer
import com.suromo.assetmanager.data.repository.AppContainerImpl
import com.suromo.common.BaseApplication

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/31
 * desc   : 应用基类
 */
class MainApplication : BaseApplication() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}