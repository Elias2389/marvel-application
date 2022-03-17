package com.ae.data.repository.characterlist

import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem

interface CharacterListRepository {

    /**
     * Method to get all Characters with paged
     *
     * @param page Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return List of Characters
     */
    suspend fun getAllCharacters(offset: Int, limit: Int): Resource<List<ResultsItem>>
}