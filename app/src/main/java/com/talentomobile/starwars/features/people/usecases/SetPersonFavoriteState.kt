package com.talentomobile.starwars.features.people.usecases

import com.talentomobile.starwars.core.interactor.UseCase
import com.talentomobile.starwars.core.functional.State
import com.talentomobile.starwars.features.people.models.view.PeopleView

class SetPersonFavoriteState(private val favoritePeopleRepository: FavoritePeopleRepository) : UseCase<Unit, PersonFavoriteParams>() {
    override fun run(params: PersonFavoriteParams?) = favoritePeopleRepository.setPersonFavoriteState(params!!.name, params.isFavorite)
}

data class PersonFavoriteParams (
    val name: String,
    val isFavorite: Boolean)
