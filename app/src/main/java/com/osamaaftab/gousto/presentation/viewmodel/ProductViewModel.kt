package com.osamaaftab.gousto.presentation.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osamaaftab.gousto.domain.usecase.GetProductsUsecase
import com.osamaaftab.network.domain.model.base.ErrorModel
import com.osamaaftab.network.domain.model.base.ResponseModel
import com.osamaaftab.gousto.domain.usecase.base.UseCaseResponse
import com.osamaaftab.gousto.presentation.base.BaseViewModel
import com.osamaaftab.network.domain.model.ProductModel


class ProductViewModel constructor(
    private val getProductsUsecase: GetProductsUsecase) :
    BaseViewModel() {


    private val _onProgressShow = MutableLiveData<Boolean>()
    private val onProgressShow: LiveData<Boolean> = _onProgressShow

    private val _onErrorShow = MutableLiveData<Boolean>()
    private val onErrorShow: LiveData<Boolean> = _onErrorShow

    private val _productList = MutableLiveData<List<ProductModel>>()
    private val productList: LiveData<List<ProductModel>> = _productList


    private val _productDetail = MutableLiveData<ProductModel>()
    private val productDetail: LiveData<ProductModel> = _productDetail

    fun getProductUseCaseResponse() = object : UseCaseResponse<ResponseModel> {
        override fun onSuccess(result: ResponseModel) {
            _onProgressShow.value = false
            _onErrorShow.value = false
            Log.i(ContentValues.TAG, "result: $result")
            _productList.value = result.data
        }

        override fun onError(errorModel: ErrorModel?) {
            Log.i(ContentValues.TAG, "error: $errorModel?.message code")
            _onProgressShow.value = false
            _onErrorShow.value = true
        }
    }

    fun getProductList(): LiveData<List<ProductModel>> {
        return productList
    }

    fun getOnErrorShow(): LiveData<Boolean> {
        return onErrorShow
    }

    fun getProduct(): LiveData<ProductModel> {
        return productDetail
    }


    fun getOnProgressShow(): LiveData<Boolean> {
        return onProgressShow
    }

    fun loadProductDetail(prroductModel: ProductModel) {
        _productDetail.value = prroductModel
    }

    fun loadProductList() {
        if (_productList.value == null) {
            _onProgressShow.value = true
            getProductsUsecase.invoke(scope, null, getProductUseCaseResponse())
        }
    }

    fun refreshProductList() {
        getProductsUsecase.invoke(scope, null, getProductUseCaseResponse())
    }
}