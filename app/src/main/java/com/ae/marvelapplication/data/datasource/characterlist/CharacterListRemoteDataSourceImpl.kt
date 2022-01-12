package com.ae.marvelapplication.data.datasource.characterlist

import com.ae.marvelappication.data.service.CharacterService
import com.ae.marvelapplication.dto.dto.MarvelResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterListRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterListRemoteDataSource {

    override suspend fun getAllCharacterListRemote(offset: Int, limit: Int): MarvelResponse {
        return characterService.getAllCharacters(offset, limit)
    }

}