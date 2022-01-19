package com.ae.marvelapplication.data.datasource.character

import com.ae.marvelapplication.data.dao.ResultItemDao
import com.ae.marvelapplication.entity.ResultsItemEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterLocalDataSourceImpl @Inject constructor(
    private val characterDao: ResultItemDao
) : CharacterLocalDataSource {

    override suspend fun getAllCharacterListLocal(
        offset: Int,
        limit: Int
    ): List<ResultsItemEntity> = characterDao.getAllCharacters(offset, limit)

    override suspend fun getCharacterListLocalById(
        id: Int
    ): ResultsItemEntity = characterDao.getCharacterListLocalById(id)

    override suspend fun saveCharacterLocal(
        character: ResultsItemEntity
    ) = characterDao.insertCharacter(character)

    override suspend fun deleteAllCharacters() {
        characterDao.deleteAllCharacters()
    }
}