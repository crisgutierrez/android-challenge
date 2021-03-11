package com.talentomobile.starwars.features.people.models.entity

import com.talentomobile.starwars.core.extensions.empty
import com.talentomobile.starwars.features.people.models.data.People

data class PeopleEntity(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: MutableList<PersonEntity>?
) {
    companion object {
        fun empty() = PeopleEntity(Int.empty(), String.empty(), String.empty(), mutableListOf())
    }

    fun toPeople() = People(count, next, previous, results?.map { it.toPerson() }?.toMutableList())
}
