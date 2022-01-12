package com.osamaaftab.network.domain.repository

import com.osamaaftab.network.domain.model.base.ResponseModel

interface ProductRepository {
    suspend fun getProducts(): ResponseModel
}