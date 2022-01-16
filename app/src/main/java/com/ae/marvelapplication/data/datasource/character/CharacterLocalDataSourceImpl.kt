package com.ae.marvelapplication.data.datasource.character

import com.ae.marvelappication.data.entity.ResultsItemEntity
import com.ae.marvelapplication.data.dao.ResultItemDao

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterLocalDataSourceImpl @Inject constructor(
    private val characterDao: ResultItemDao
) : CharacterLocalDataSource {

    override suspend fun getAllCharacterListLocal(
        offset: Int,
        limit: Int
    ): List<ResultsItemEntity> {
        return characterDao.getAllCharacters(offset, limit)
    }

    override suspend fun saveCharacterLocal(character: ResultsItemEntity) {
        characterDao.insertCharacter(character)
    }
}