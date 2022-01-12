package com.osamaaftab.network.domain.model

import android.os.Parcelable
import com.osamaaftab.network.domain.model.base.ImageModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    val title: String,
    val link: String,
    val description: String,
    val images : ImageModel?,
    val list_price: String
) : Parcelable