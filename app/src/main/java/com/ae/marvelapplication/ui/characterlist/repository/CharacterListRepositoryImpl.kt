package com.ae.marvelapplication.ui.characterlist.repository

import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.dto.dto.ResultsItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CharacterListRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharacterListRepository {

    override suspend fun getAllCharacters(page: Int, limit: Int): List<ResultsItem> {
        val list = mutableListOf<ResultsItem>()
        return withContext(Dispatchers.IO) {
            list.addAll(
                remoteDataSource.getAllCharacterListByPageRemote(
                    page,
                    limit
                ).data.results
            )
             list
        }
    }
}
