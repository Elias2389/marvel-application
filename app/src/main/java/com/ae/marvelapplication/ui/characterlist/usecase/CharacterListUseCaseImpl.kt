package com.ae.marvelapplication.ui.characterlist.usecase

import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.ui.characterlist.repository.CharacterListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterListUseCaseImpl @Inject constructor(
    private val repository: CharacterListRepository
) : CharacterListUseCase {

    override suspend fun invoke(offset: Int, limit: Int): Resource<List<ResultsItem>> {
        return repository.getAllCharacters(offset, limit)
    }
}