package com.osamaaftab.network.domain.model.base

import android.os.Parcelable
import com.osamaaftab.network.domain.model.ProductModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseModel(
    val data: List<ProductModel>,
) : Parcelable