package com.talentomobile.starwars.features.people.usecases

import com.talentomobile.starwars.core.interactor.UseCase
import com.talentomobile.starwars.core.functional.State
import com.talentomobile.starwars.features.people.models.view.PeopleView

class GetPeople(private val peopleRepository: PeopleRepository) : UseCase<State<PeopleView>, UseCase.None>() {
    override fun run(params: None?) = peopleRepository.getPeople()
}
