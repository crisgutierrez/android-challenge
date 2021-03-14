package com.talentomobile.starwars.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.talentomobile.starwars.core.database.dao.FavoritePeopleDao
import com.talentomobile.starwars.core.database.entity.FavoritePeopleEntity

@Database(
    entities = [
        FavoritePeopleEntity::class
    ],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun favoritePeopleDao(): FavoritePeopleDao
}