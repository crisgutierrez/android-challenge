package com.talentomobile.starwars.core.di

import com.talentomobile.starwars.features.people.usecases.*
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetPeople(get<PeopleRepository>()) }
    factory { GetPersonFavoriteState(get<FavoritePeopleRepository>()) }
    factory { SetPersonFavoriteState(get<FavoritePeopleRepository>()) }

}
