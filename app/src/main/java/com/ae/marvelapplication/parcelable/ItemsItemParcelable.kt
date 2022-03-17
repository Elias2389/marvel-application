package com.ae.marvelapplication.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsItemParcelable(
    val name: String = "",
    val resourceURI: String = ""
) : Parcelable