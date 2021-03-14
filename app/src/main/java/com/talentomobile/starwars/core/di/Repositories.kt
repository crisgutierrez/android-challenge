package com.talentomobile.starwars.core.di

import com.talentomobile.starwars.features.people.usecases.FavoritePeopleRepository
import com.talentomobile.starwars.features.people.usecases.PeopleRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<PeopleRepository> { PeopleRepository.Network(get(), get()) }
    factory<FavoritePeopleRepository> { FavoritePeopleRepository.Database(get(), get()) }

}
