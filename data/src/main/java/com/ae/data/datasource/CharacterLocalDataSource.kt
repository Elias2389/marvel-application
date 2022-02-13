package com.ae.data.datasource

import com.ae.domain.model.ResultsItem


interface CharacterLocalDataSource {

    /**
     * Method to get all Characters with paged from local db
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return Response from DB
     */
    suspend fun getAllCharacterListLocal(offset: Int, limit: Int): List<ResultsItem>

    /**
     * Method to get all Characters with paged from local db
     *
     * @param id Item
     * @return Response from DB
     */
    suspend fun getCharacterListLocalById(id: Int): ResultsItem

    /**
     * Method to save each Character in local db
     *
     * @param character
     */
    suspend fun saveCharacterLocal(character: ResultsItem)

    /**
     * Delete All items
     */
    suspend fun deleteAllCharacters()
}