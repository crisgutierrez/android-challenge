package com.talentomobile.starwars.core.di

import com.talentomobile.starwars.features.people.usecases.PeopleRepository

val repositoryModule = module {

    factory<PeopleRepository> { PeopleRepository.Network(get(), get()) }

}
