package com.ae.marvelapplication.data.datasource.characterlist

import com.ae.marvelapplication.dto.dto.CharactersResponse

interface CharacterListRemoteDataSource {

    /**
     * Method to get all Characters with paged from remote service
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return Response from Marvel's API
     */
    suspend fun getAllCharacterListByPageRemote(offset: Int, limit: Int): CharactersResponse
}