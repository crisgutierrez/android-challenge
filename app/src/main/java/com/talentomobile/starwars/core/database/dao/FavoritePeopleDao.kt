package com.talentomobile.starwars.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.talentomobile.starwars.core.database.entity.FavoritePeopleEntity

@Dao
interface FavoritePeopleDao {

    //INSERT or UPDATE if exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePerson(favoritePeopleEntity: FavoritePeopleEntity)


    //GET
    @Query("SELECT * FROM ${FavoritePeopleEntity.TABLE_NAME}")
    suspend fun getAllFavoritePeople(): List<FavoritePeopleEntity>

    @Query("SELECT isFavorite FROM ${FavoritePeopleEntity.TABLE_NAME} WHERE name LIKE :name")
    suspend fun getFavoritePersonById(name: String): Boolean


    //DELETE
    @Query("DELETE FROM ${FavoritePeopleEntity.TABLE_NAME} WHERE name LIKE :name")
    suspend fun deleteFavoritePersonById(name: String)

    @Query("DELETE FROM ${FavoritePeopleEntity.TABLE_NAME}")
    suspend fun clearFavoritePeopleTable()

}