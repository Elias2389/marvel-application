package com.ae.marvelapplication.ui.characterdetail.repository

import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.dto.dto.ResultsItem
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
    ): Resource<List<ResultsItem>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = remote.getCharacterById(characterId).data.results
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}