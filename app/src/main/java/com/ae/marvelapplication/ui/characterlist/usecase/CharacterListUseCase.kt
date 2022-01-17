package com.ae.marvelapplication.ui.characterlist.usecase

import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.dto.dto.ResultsItem


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