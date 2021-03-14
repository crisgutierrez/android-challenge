package com.talentomobile.starwars.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoritePeopleEntity.TABLE_NAME)
data class FavoritePeopleEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")       val name: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
    ) {

    companion object {
        const val TABLE_NAME = "favoritePeople"
    }
}
