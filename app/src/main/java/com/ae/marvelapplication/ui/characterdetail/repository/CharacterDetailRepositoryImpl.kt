package com.ae.marvelapplication.ui.characterdetail.repository

import com.ae.marvelapplication.data.datasource.character.CharacterLocalDataSource
import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.dto.dto.ResultsItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CharacterDetailRepositoryImpl @Inject constructor(
    private val remote: CharactersRemoteDataSource
) : CharacterDetailRepository {

    override suspend fun getCharacterById(characterId: Int): List<ResultsItem> {
        val list = mutableListOf<ResultsItem>()
        withContext(Dispatchers.IO) {
            remote.getCharacterById (characterId).data.results.let {
                list.addAll(it)
            }
        }
        return list
    }
}