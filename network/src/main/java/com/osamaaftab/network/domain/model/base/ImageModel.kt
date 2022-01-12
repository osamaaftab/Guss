package com.osamaaftab.network.domain.model.base

import android.os.Parcelable
import com.osamaaftab.network.domain.model.SubImageModel
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(
    @field:Json(name = "750")
    val image : SubImageModel?,
) : Parcelable



