package com.ae.marvelapplication.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbnailParcelable(
    val path: String = "",
    val extension: String = ""
) : Parcelable