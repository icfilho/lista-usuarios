package com.isaiasfilho.listausuario

import com.isaiasfilho.listausuario.data.db.AppDatabase
import com.isaiasfilho.listausuario.network.ApiService
import com.isaiasfilho.listausuario.data.repository.UserRepository
import com.isaiasfilho.listausuario.ui.lista.ListaUsuarioViewModel
import com.isaiasfilho.listausuario.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModules : Module = module {

    single { AppDatabase.instance(get()) }
    single { ApiService }
    single{UserRepository(get(),get())}

    viewModel { SplashViewModel(get()) }
    viewModel { ListaUsuarioViewModel(get()) }
}
