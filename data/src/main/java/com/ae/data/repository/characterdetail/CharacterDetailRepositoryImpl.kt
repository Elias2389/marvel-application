package com.ae.data.repository.characterdetail

import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CharacterDetailRepositoryImpl @Inject constructor(
    private val remote: CharactersRemoteDataSource,
) : CharacterDetailRepository {

    override suspend fun getCharacterById(
        characterId: Int
    ): Resource<List<ResultsItem>> {
        val result = remote.getCharacterById(characterId).data.results
        return withContext(Dispatchers.IO) { Resource.Success(result) }
    }
}