package com.osamaaftab.gousto.di.module

import com.osamaaftab.gousto.presentation.viewmodel.ProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ActivityModule = module {
    viewModel { ProductViewModel(get()) }
}