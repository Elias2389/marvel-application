package com.ae.marvelapplication.ui.characterdetail.usercase

import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.ui.characterdetail.repository.CharacterDetailRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDetailUserCaseImpl @Inject constructor(
    private val repository: CharacterDetailRepository
): CharacterDetailUserCase {

    override suspend fun invoke(characterId: Int): List<ResultsItem> {
        return repository.getCharacterById(characterId)
    }
}