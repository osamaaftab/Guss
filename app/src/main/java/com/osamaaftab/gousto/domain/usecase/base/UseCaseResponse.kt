package com.osamaaftab.gousto.domain.usecase.base

import com.osamaaftab.network.domain.model.base.ErrorModel

interface UseCaseResponse<in Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}