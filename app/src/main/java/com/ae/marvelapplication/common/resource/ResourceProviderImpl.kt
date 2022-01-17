package com.ae.marvelapplication.common.resource

import android.content.Context
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(private val context: Context) : ResourceProvider {

    override fun getString(id: Int): String = context.getString(id)
}