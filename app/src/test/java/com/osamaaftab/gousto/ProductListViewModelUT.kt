package com.osamaaftab.gousto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.osamaaftab.network.domain.model.base.ResponseModel
import com.osamaaftab.gousto.domain.usecase.GetProductsUsecase
import com.osamaaftab.gousto.presentation.viewmodel.ProductViewModel
import com.osamaaftab.gousto.util.ApiErrorHandle
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductListViewModelUT {


    @RelaxedMockK
    lateinit var getProductsUsecase: GetProductsUsecase


    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var productViewModel: ProductViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        productViewModel = ProductViewModel(getProductsUsecase)
    }

    @Test
    fun onSuccess() {
        val responseModel = mockk<ResponseModel>(relaxed = true)
        productViewModel.getProductUseCaseResponse().onSuccess(responseModel)
        val response = productViewModel.getProductList().value
        val state = productViewModel.getOnProgressShow().value
        Assert.assertEquals(false, state)
        Assert.assertEquals(responseModel.data, response)
    }

    @Test
    fun onFails() {
        val apiErrorHandle = ApiErrorHandle()
        val throwable = mockk<Throwable>()
        apiErrorHandle.traceErrorException(throwable)
        productViewModel.getProductUseCaseResponse()
            .onError(apiErrorHandle.traceErrorException(throwable))
        val state = productViewModel.getOnProgressShow().value
        val drawable = productViewModel.getOnErrorShow().value
        Assert.assertEquals(true, drawable)
        Assert.assertEquals(false, state)
    }

    @Test
    fun onLoad() {
        productViewModel.loadProductList()
        val state = productViewModel.getOnProgressShow().value
        Assert.assertEquals(true, state)
    }

    @Test
    fun onRefresh() {
        productViewModel.refreshProductList()
    }
}