package com.ae.marvelapplication.common.reponse

import com.ae.marvelappication.common.reponse.Resource

interface ResponseHandler {

    fun <T : Any> handleSuccess(data: T): Resource<T>

    fun <T : Any> handleException(e: Exception): Resource<T>
}