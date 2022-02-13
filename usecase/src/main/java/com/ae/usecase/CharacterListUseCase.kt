package com.ae.usecase

import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem

interface CharacterListUseCase {

    /**
     * Method to invoke get all Characters with paged
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return List of Characters
     */
    suspend fun invoke(offset: Int, limit: Int): Resource<List<ResultsItem>>
}