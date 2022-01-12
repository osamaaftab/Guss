package com.osamaaftab.network.di

import com.osamaaftab.network.data.source.remote.ProductServices
import retrofit2.Retrofit

object ApiserviceModule {


    val PRODUCT_SERVICES: ProductServices by lazy {
        provideProductServices(
            retrofit = NetworkModule.retrofit
        )
    }


    private fun provideProductServices(retrofit: Retrofit): ProductServices {
        return retrofit.create(ProductServices::class.java)
    }
}