package com.talentomobile.starwars.core.di

import com.talentomobile.starwars.features.people.usecases.GetPeople
import com.talentomobile.starwars.features.people.usecases.PeopleRepository
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetPeople(get<PeopleRepository>()) }

}
