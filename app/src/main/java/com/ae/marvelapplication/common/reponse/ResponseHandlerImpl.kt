package com.ae.marvelapplication.common.reponse

import com.ae.marvelappication.common.reponse.Resource
import com.ae.marvelapplication.R
import com.ae.marvelapplication.common.resource.ResourceProvider

/**
 * Class to handler errors of service with coroutines
 */
class ResponseHandlerImpl(private val resourceProvider: ResourceProvider) : ResponseHandler {

    override fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    override fun <T : Any> handleException(e: Exception): Resource<T> {
        return e.message?.let {
            Resource.error(null, it)
        } ?: kotlin.run {
            Resource.error(null, resourceProvider.getString(R.string.character_app_general_error))
        }
    }
}