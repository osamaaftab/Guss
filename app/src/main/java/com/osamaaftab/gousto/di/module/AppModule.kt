package com.osamaaftab.gousto.di.module

import com.osamaaftab.gousto.util.ApiErrorHandle
import org.koin.dsl.module

val AppModule = module {
    single { provideApiError() }
}

fun provideApiError(): ApiErrorHandle {
    return ApiErrorHandle()
}