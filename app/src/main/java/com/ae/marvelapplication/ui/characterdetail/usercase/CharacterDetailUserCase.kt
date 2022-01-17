package com.ae.marvelapplication.ui.characterdetail.usercase

import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.dto.dto.ResultsItem


interface CharacterDetailUserCase {

    /**
     * Method to call get Character by id
     *
     * @param characterId id of character
     * @return List of Characters
     */
    suspend fun invoke(characterId: Int): Resource<List<ResultsItem>>
}