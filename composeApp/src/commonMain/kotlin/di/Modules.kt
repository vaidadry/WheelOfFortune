package di

import SpinWheelViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

//expect val platformModule: Module

val sharedModule = module {
//    singleOf(::MainRepositoryImpl).bind<MainRepository>()
    viewModelOf(::SpinWheelViewModel)
}