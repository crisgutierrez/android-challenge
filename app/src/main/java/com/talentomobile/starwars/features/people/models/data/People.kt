package com.talentomobile.starwars.features.people.models.data

import com.talentomobile.starwars.features.people.models.view.PeopleView

data class People(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: MutableList<Person>?
) {

    fun toPeopleView() =
        PeopleView(count, next, previous, results?.map { it.toPersonView() }?.toMutableList())
}
