package com.ae.usecase

import com.ae.data.repository.characterlist.CharacterListRepository
import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem
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