package com.osamaaftab.gousto.di.module

import com.osamaaftab.gousto.domain.usecase.GetProductsUsecase
import com.osamaaftab.gousto.util.ApiErrorHandle
import com.osamaaftab.network.di.RepositoryModule.PRODUCT_REPOSITORY
import com.osamaaftab.network.domain.repository.ProductRepository
import org.koin.dsl.module

val UseCaseModule = module {
    single { provideGetQestionUseCase(PRODUCT_REPOSITORY, provideApiError()) }

}

fun provideGetQestionUseCase(
    productRepository: ProductRepository,
    apiErrorHandle: ApiErrorHandle
): GetProductsUsecase {
    return GetProductsUsecase(productRepository, apiErrorHandle)
}