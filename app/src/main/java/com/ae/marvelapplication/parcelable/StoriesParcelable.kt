package com.ae.marvelapplication.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoriesParcelable(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: List<ItemsItemParcelable> = emptyList()
) : Parcelable