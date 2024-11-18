package ir.misterdeveloper.aramkadeapplication.di

import ir.misterdeveloper.aramkadeapplication.net.createApiService
import ir.misterdeveloper.aramkadeapplication.repository.category.MethodRepository
import ir.misterdeveloper.aramkadeapplication.repository.category.MethodRepositoryImpl
import ir.misterdeveloper.aramkadeapplication.ui.features.main.MainViewModel
import ir.misterdeveloper.aramkadeapplication.ui.features.meetings.MeetingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val myModules = module {


    single { createApiService() }
    single<MethodRepository> {
        MethodRepositoryImpl(
            get()
        )
    }
    viewModel { (itemId: Int) -> MeetingViewModel(get(), itemId) }
    viewModel { (checkInternet: Boolean, catId: Int) -> MainViewModel(get(), checkInternet, catId) }


}

