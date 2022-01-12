package com.osamaaftab.gousto.domain.usecase

import com.osamaaftab.network.domain.model.base.ResponseModel
import com.osamaaftab.gousto.domain.usecase.base.UseCase
import com.osamaaftab.gousto.util.ApiErrorHandle
import com.osamaaftab.network.domain.repository.ProductRepository

class GetProductsUsecase constructor(
    private val productRepository: ProductRepository,
    apiErrorHandle: ApiErrorHandle?
) :
    UseCase<ResponseModel, Any?>(apiErrorHandle) {

    override suspend fun run(params: Any?): ResponseModel {
        return productRepository.getProducts()
    }
}