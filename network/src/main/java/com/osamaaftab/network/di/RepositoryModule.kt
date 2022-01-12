package com.osamaaftab.network.di

import com.osamaaftab.network.data.repository.ProductRepositoryImp
import com.osamaaftab.network.data.source.remote.ProductServices
import com.osamaaftab.network.domain.repository.ProductRepository

object RepositoryModule {


    val PRODUCT_REPOSITORY: ProductRepository by lazy {
        provideProductRepository(
            productServices = ApiserviceModule.PRODUCT_SERVICES
        )
    }

    private fun provideProductRepository(productServices: ProductServices): ProductRepository {
        return ProductRepositoryImp(productServices)
    }
}