package com.ae.usecase

import com.ae.domain.model.ResultsItem
import com.ae.data.repository.characterdetail.CharacterDetailRepository
import com.ae.domain.model.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDetailUserCaseImpl @Inject constructor(
    private val repository: CharacterDetailRepository
) : CharacterDetailUserCase {

    override suspend fun invoke(characterId: Int): Resource<List<ResultsItem>> {
        return repository.getCharacterById(characterId)
    }
}