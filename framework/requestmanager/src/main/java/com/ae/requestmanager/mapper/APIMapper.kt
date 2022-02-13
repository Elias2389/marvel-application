package com.ae.requestmanager.mapper

import com.ae.domain.model.CharactersResponse
import com.ae.requestmanager.model.CharactersResponseServer

fun CharactersResponseServer.toCharactersResponse() = CharactersResponse(
    copyright = copyright
)