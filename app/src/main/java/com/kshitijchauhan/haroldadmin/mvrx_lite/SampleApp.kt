package com.kshitijchauhan.haroldadmin.mvrx_lite

import android.app.Application
import org.koin.android.ext.android.startKoin

class SampleApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, homeModule))
    }
}