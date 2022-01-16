package com.ae.marvelapplication.data.datasource.character

import com.ae.marvelappication.data.entity.ResultsItemEntity

interface CharacterLocalDataSource {

    /**
     * Method to get all Characters with paged from local db
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return Response from DB
     */
    suspend fun getAllCharacterListLocal(offset: Int, limit: Int): List<ResultsItemEntity>

    /**
     * Method to save each Character in local db
     *
     * @param character
     */
    suspend fun saveCharacterLocal(character: ResultsItemEntity)
}