package com.talentomobile.starwars.features.people.usecases

import com.talentomobile.starwars.core.database.dao.FavoritePeopleDao
import com.talentomobile.starwars.core.database.entity.FavoritePeopleEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface FavoritePeopleRepository {

    fun getPersonFavoriteState(name: String): Flow<Boolean?>

    fun setPersonFavoriteState(name: String, isFavorite: Boolean): Flow<Unit>

    class Database(
        private val ioDispatcher: CoroutineDispatcher,
        private val favoritePeopleDao: FavoritePeopleDao
    ) : FavoritePeopleRepository {

        override fun getPersonFavoriteState(name: String) =
            flow {
                emit(favoritePeopleDao.getFavoritePersonById(name))
            }
                .flowOn(ioDispatcher)

        override fun setPersonFavoriteState(name: String, isFavorite: Boolean) =
            flow {
                emit(insertFavorite(name, isFavorite))
            }
                .flowOn(ioDispatcher)

        private suspend fun insertFavorite(name: String, isFavorite: Boolean) =
            favoritePeopleDao.insertFavoritePerson(FavoritePeopleEntity(name, isFavorite))
    }
}
