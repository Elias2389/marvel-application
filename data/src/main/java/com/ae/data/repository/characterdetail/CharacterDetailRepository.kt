package com.ae.data.repository.characterdetail

import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem

interface CharacterDetailRepository {

    /**
     * Method to get Character by id
     *
     * @param characterId id of character
     * @return List of Characters
     */
    suspend fun getCharacterById(characterId: Int): Resource<List<ResultsItem>>
}