package com.kshitijchauhan.haroldadmin.mvrx_lite

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { NumbersRepository() }
}

val homeModule = module {
    viewModel { (initialState: State.HomeState) ->
        HomeViewModel(initialState, get<NumbersRepository>())
    }

    factory { HomeEpoxyController() }
}