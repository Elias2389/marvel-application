package com.ae.marvelapplication.ui.characterlist.repository

import com.ae.marvelapplication.common.connectionchecker.CheckConnection
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.data.datasource.character.CharacterLocalDataSource
import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.mapper.toEntity
import com.ae.marvelapplication.mapper.toResultsItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CharacterListRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val local: CharacterLocalDataSource,
    private val checkConnect: CheckConnection
) : CharacterListRepository {

    override suspend fun getAllCharacters(
        page: Int,
        limit: Int
    ): Resource<List<ResultsItem>> = withContext(Dispatchers.IO) {
        return@withContext try {
            if (checkConnect.connectionIsAvailable()) {
                remoteDataSource.getAllCharacterListByPageRemote(
                    page,
                    limit
                ).data.results.map { item ->
                    local.saveCharacterLocal(item.toEntity())
                }
                Resource.Success(local.getAllCharacterListLocal(page, limit).toResultsItem())
            } else {
                Resource.Success(local.getAllCharacterListLocal(page, limit).toResultsItem())
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}