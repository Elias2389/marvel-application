package com.ae.data.repository.characterlist

import com.ae.data.connectionchecker.CheckConnection
import com.ae.data.datasource.CharacterLocalDataSource
import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem
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
        offset: Int,
        limit: Int
    ): Resource<List<ResultsItem>> = withContext(Dispatchers.IO) {
        return@withContext try {
            if (checkConnect.connectionIsAvailable()) {
                if (offset == 0) {
                    local.deleteAllCharacters()
                }
                remoteDataSource.getAllCharacterListByPageRemote(
                    offset,
                    limit
                ).data.results.map { item ->
                    local.saveCharacterLocal(item)
                }
                Resource.Success(local.getAllCharacterListLocal(offset, limit))
            } else {
                Resource.Success(local.getAllCharacterListLocal(offset, limit))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}