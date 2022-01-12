package com.osamaaftab.network.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubImageModel(
    val src: String?,
    val url: String?,
    val width: Int?,

    ) : Parcelable