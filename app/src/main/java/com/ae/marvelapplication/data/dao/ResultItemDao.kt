package com.ae.marvelapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ae.marvelapplication.entity.ResultsItemEntity

@Dao
interface ResultItemDao {
    /**
     * Method to get data from DB
     *
     * @return results
     */
    @Query("SELECT DISTINCT * FROM character ORDER BY name ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllCharacters(offset: Int, limit: Int): List<ResultsItemEntity>

    /**
     * Insert results in DB
     *
     * @param character to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: ResultsItemEntity)

    /**
     * Method to get all Characters with paged from local db
     *
     * @param id Item
     * @return Response from DB
     */
    @Query("SELECT * FROM character WHERE idCharacter = :id")
    suspend fun getCharacterListLocalById(id: Int): ResultsItemEntity

    /**
     * Delete all Characters.
     */
    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()
}