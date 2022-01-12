package com.osamaaftab.network.data.repository

import com.osamaaftab.network.domain.model.base.ResponseModel
import com.osamaaftab.network.data.source.remote.ProductServices
import com.osamaaftab.network.domain.repository.ProductRepository

class ProductRepositoryImp(private val productServices: ProductServices) : ProductRepository {


    override suspend fun getProducts(): ResponseModel {
        return productServices.getProductsAsync()
            .await()
    }
}