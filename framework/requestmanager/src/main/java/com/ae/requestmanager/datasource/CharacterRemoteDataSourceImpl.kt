package com.ae.requestmanager.datasource

import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.data.service.CharacterService
import com.ae.domain.model.CharactersResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharactersRemoteDataSource {

    override suspend fun getAllCharacterListByPageRemote(
        offset: Int,
        limit: Int
    ): CharactersResponse = characterService.getAllCharacters(offset, limit)

    override suspend fun getCharacterById(
        characterId: Int
    ): CharactersResponse = characterService.getCharacterById(characterId)
}
