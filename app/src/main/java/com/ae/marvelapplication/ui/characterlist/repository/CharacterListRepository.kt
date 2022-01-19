package com.ae.marvelapplication.ui.characterlist.repository

import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.dto.dto.ResultsItem

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