package com.talentomobile.starwars.features.people.usecases

import com.talentomobile.starwars.core.interactor.UseCase
import com.talentomobile.starwars.core.functional.State
import com.talentomobile.starwars.features.people.models.view.PeopleView

class GetPersonFavoriteState(private val favoritePeopleRepository: FavoritePeopleRepository) : UseCase<Boolean?, String>() {
    override fun run(params: String?) = favoritePeopleRepository.getPersonFavoriteState(params!!)
}
