package com.isaiasfilho.listausuario

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppUsuario: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppUsuario)
            modules(listOf(appModules))
        }
    }
}