package com.ae.usecase

import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem

interface CharacterDetailUserCase {

    /**
     * Method to call get Character by id
     *
     * @param characterId id of character
     * @return List of Characters
     */
    suspend fun invoke(characterId: Int): Resource<List<ResultsItem>>
}