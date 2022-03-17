package com.ae.marvelapplication.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlsItemParcelable(
    val type: String = "",
    val url: String = ""
) : Parcelable