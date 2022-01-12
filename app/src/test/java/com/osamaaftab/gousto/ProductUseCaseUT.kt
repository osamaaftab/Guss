package com.osamaaftab.gousto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.osamaaftab.network.domain.model.base.ResponseModel
import com.osamaaftab.gousto.domain.usecase.GetProductsUsecase
import com.osamaaftab.gousto.util.ApiErrorHandle
import com.osamaaftab.network.data.repository.ProductRepositoryImp
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductUseCaseUT {


    lateinit var getProductsUsecase: GetProductsUsecase


    @MockK
    lateinit var productRepositoryImp: ProductRepositoryImp

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        val apiErrorHandle = mockk<ApiErrorHandle>()
        getProductsUsecase = GetProductsUsecase(productRepositoryImp, apiErrorHandle)
    }


    @Test
    fun getProducts() = runBlocking {
        val repositories = mockk<ResponseModel>()

        every { runBlocking { productRepositoryImp.getProducts() } } returns (repositories)
        val expectedPost = productRepositoryImp.getProducts()
        every { runBlocking { getProductsUsecase.run() } } returns (repositories)
        val expectedResult = getProductsUsecase.run()
        assertEquals(expectedPost, expectedResult)
    }
}