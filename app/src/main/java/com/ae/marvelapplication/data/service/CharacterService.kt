package com.ae.marvelapplication.data.service

import com.ae.marvelapplication.dto.dto.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    /**
     * Get all characters from API
     */
    @GET("characters")
    suspend fun getAllCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharactersResponse

    /**
     * Get character by id characters from API
     */
    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int
    ): CharactersResponse
}