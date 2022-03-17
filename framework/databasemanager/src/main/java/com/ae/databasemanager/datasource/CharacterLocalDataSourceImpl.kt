package com.ae.databasemanager.datasource


import com.ae.data.datasource.CharacterLocalDataSource
import com.ae.databasemanager.dao.ResultItemDao
import com.ae.databasemanager.mapper.toEntity
import com.ae.databasemanager.mapper.toResultsItem
import com.ae.domain.model.ResultsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterLocalDataSourceImpl @Inject constructor(
    private val characterDao: ResultItemDao
) : CharacterLocalDataSource {

    override suspend fun getAllCharacterListLocal(
        offset: Int,
        limit: Int
    ): List<ResultsItem> = characterDao.getAllCharacters(offset, limit).toResultsItem()

    override suspend fun getCharacterListLocalById(
        id: Int
    ): ResultsItem = characterDao.getCharacterListLocalById(id).toResultsItem()

    override suspend fun saveCharacterLocal(
        character: ResultsItem
    ) = characterDao.insertCharacter(character.toEntity())

    override suspend fun deleteAllCharacters() {
        characterDao.deleteAllCharacters()
    }
}