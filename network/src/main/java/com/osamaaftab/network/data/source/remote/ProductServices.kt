package com.osamaaftab.network.data.source.remote

import com.osamaaftab.network.domain.model.base.ResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductServices {

    @GET("products")
    fun getProductsAsync(@Query("image_sizes[]") image_sizes: IntArray = intArrayOf(750)): Deferred<ResponseModel>
}