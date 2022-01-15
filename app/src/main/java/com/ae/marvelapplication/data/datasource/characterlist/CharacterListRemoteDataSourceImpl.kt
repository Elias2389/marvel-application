package com.ae.marvelapplication.data.datasource.characterlist

import com.ae.marvelapplication.data.service.CharacterService
import com.ae.marvelapplication.dto.dto.CharactersResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterListRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterListRemoteDataSource {

    override suspend fun getAllCharacterListByPageRemote(
        offset: Int,
        limit: Int
    ): CharactersResponse {
        return characterService.getAllCharacters(offset, limit)
    }
}