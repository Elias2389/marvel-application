package com.ae.marvelapplication.data.datasource.character

import com.ae.marvelapplication.data.service.CharacterService
import com.ae.marvelapplication.dto.dto.CharactersResponse
import com.ae.marvelapplication.dto.dto.ResultsItem
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
